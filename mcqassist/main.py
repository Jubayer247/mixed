import gtts
tts = gtts.gTTS(text='Hello', lang='en')
tts.save("hello.mp3")