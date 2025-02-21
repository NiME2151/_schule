from django import forms

from polls.models import Choice


class VoteForm(forms.Form):
    choice = forms.ModelChoiceField(queryset=Choice.objects.none(), empty_label=None)

    def __init__(self, *args, **kwargs):
        question = kwargs.pop('question')
        super().__init__(*args, **kwargs)
        self.fields['choice'].queryset = question.choice_set.all()