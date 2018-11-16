from django import forms
from fileUpload.models import NewUpload
class UploadFileForm(forms.ModelForm):
    class Meta:
        model=NewUpload
        fields="__all__"


