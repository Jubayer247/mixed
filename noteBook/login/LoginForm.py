from django import forms
from login.models import NewLogin

class LogForm(forms.ModelForm):
    password=forms.CharField(widget=forms.PasswordInput)
    class Meta:
        model=NewLogin
        fields=['email','password',]

