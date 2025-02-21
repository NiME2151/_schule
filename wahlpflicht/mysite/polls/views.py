from os import error

from django.db.models import F
from django.http import HttpResponseRedirect
from django.shortcuts import get_object_or_404, render
from django.urls import reverse
from django.views import generic

from .forms import VoteForm
from .models import Choice, Question

class IndexView(generic.ListView):
    template_name = "polls/index.html"
    context_object_name = "latest_question_list"

    def get_queryset(self):
        """Return the last five published questions."""
        return Question.objects.order_by("-pub_date")[:5]


class DetailView(generic.DetailView):
    model = Question
    template_name = "polls/detail.html"


class ResultsView(generic.DetailView):
    model = Question
    template_name = "polls/results.html"

class VoteView(generic.FormView):
    form_class = VoteForm
    template_name = "polls/detail.html"

    def get_form_kwargs(self):
        kwargs = super().get_form_kwargs()
        kwargs['question'] = get_object_or_404(Question, pk=self.kwargs['question_id'])
        return kwargs

    def form_valid(self, form):
        selected_choice = form.cleaned_data['choice']
        selected_choice.votes = F('votes') + 1
        selected_choice.save()
        return HttpResponseRedirect(reverse('polls:results', args=(selected_choice.question.id,)))

    def form_invalid(self, form):
        question = get_object_or_404(Question, pk=self.kwargs['question_id'])
        return render(self.request, self.template_name, {
            'question': question,
            'form': form,
            'error_message': "You didn't select a choice"
        })

# old unused stuff
def vote(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    try:
        selected_choice = question.choice_set.get(pk=request.POST["choice"])
    except (KeyError, Choice.DoesNotExist):
        # Redisplay the question voting form.
        return render(
            request,
            "polls/detail.html",
            {
                "question": question,
                "error_message": "You didn't select a choice.",
            },
        )
    else:
        selected_choice.votes = F("votes") + 1
        selected_choice.save()
        return HttpResponseRedirect(reverse("polls:results", args=(question.id,)))