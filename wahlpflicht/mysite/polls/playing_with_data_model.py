import os
import sys

import django

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "mysite.settings")
django.setup()
print('Python %s on %s' % (sys.version, sys.platform))
print('Django %s' % django.get_version())
print("Django Environment variable DJANGO_SETTINGS_MODULE is set to: '%s'" % (os.getenv("DJANGO_SETTINGS_MODULE")))

from mysite import settings
from polls.models import Question, Choice
from django.utils import timezone

print(Question.objects.all())

