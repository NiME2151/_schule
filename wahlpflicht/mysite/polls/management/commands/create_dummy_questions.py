import random
from django.core.management.base import BaseCommand
from django.utils import timezone
from faker import Faker
from polls.models import Question, Choice

class Command(BaseCommand):
    help = 'Erzeugt 100 Dummy-Fragen mit jeweils 4 zugehörigen Antworten.'

    def handle(self, *args, **kwargs):
        faker = Faker()

        if Question.objects.filter(question_text__startswith="Dummy Question:").exists():
            self.stdout.write(self.style.WARNING('Dummy-Daten existieren bereits. Es werden keine neuen Daten erstellt.'))
        else:
            for _ in range(100):
                question_text = f"Dummy Question: {faker.sentence()}"
                question = Question.objects.create(question_text=question_text, pub_date=timezone.now())

                for _ in range(4):
                    choice_text = faker.sentence()
                    Choice.objects.create(question=question, choice_text=choice_text, votes=random.randint(0, 100))

            self.stdout.write(self.style.SUCCESS('100 Dummy-Fragen und deren Antworten wurden erfolgreich erstellt.'))
