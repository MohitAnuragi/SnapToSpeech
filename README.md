
# 📄🔊 Snap To Speech

---

## 📄 Description

**Snap(Image) to Audio ** is a powerful Android application that enables users to **listen to text content from images**. It combines the capabilities of:

 **Snap to Speech** – Capture images with text (handwritten or printed) and convert them into spoken audio using OCR.

Ideal for:
- Students revising notes
- Visually impaired users
- Language learners
- People who prefer auditory content over reading

---

## 📚 Table of Contents

- [Features](#features)
- [Usage](#usage)
- [Tech Stack](#tech-stack)
- [Challenges & Solutions](#challenges--solutions)
- [Future Improvements](#future-improvements)
- [Libraries Used](#libraries--used)
- [Contact](#contact)

---

## ✅ Features

- 📄 Upload and listen to PDF documents
- 📷 Capture or select an image and extract text using OCR
- 🔊 Convert extracted text to speech (TTS)
- 🗂️ Categorize PDFs by Subject and Type
- 📚 Real-time sync using **Supabase**
- 🗣️ Multi-language OCR and TTS support
- 🎨 Jetpack Compose UI with modern UX
- 📶 Offline capability for text-to-speech

---

## 🧑‍💻 Usage

1. Choose between:
   - **Snap Image** – Use camera or gallery to extract image text

2. View extracted text

3. Control playback:
   - Play / Pause / Resume / Stop

4. For PDFs: Filter documents by **Subject** and **Type**

---

## 💻 Tech Stack

| Feature           | Technology                             |
|------------------|-----------------------------------------|
| UI Framework      | Jetpack Compose                        |
| Architecture      | MVVM + Repository                      |
| Text Recognition  | ML Kit Text Recognition                |
| PDF Parsing       | PdfBox-Android or PdfRenderer          |
| Audio Playback    | Android TextToSpeech API               |
| Cloud Sync        | Supabase (DB + Storage)                |
| Local DB          | Room                                   |
| Image Capture     | CameraX                                |
| Image Picker      | MediaStore API                         |
| Image Loading     | Coil                                   |

---

## ⚔️ Challenges & Solutions

| Challenge                        | Solution                                                        |
|----------------------------------|-----------------------------------------------------------------|
| Extracting clean text from PDFs  | Used PdfBox-Android with fallback to PdfRenderer                |
| Real-time audio playback control | Managed TTS state via ViewModel                                 |
| OCR from noisy images            | Used ML Kit’s robust on-device OCR with image preprocessing     |
| Real-time sync of PDF metadata   | Used Supabase real-time and cloud storage                       |

---

## 🚀 Future Improvements

- [ ] Export audio as `.mp3`
- [ ] Text highlighting during audio playback
- [ ] Save extracted OCR/PDF text for review
- [ ] Voice-controlled TTS playback
- [ ] Translation before speech (OCR mode)

---

## Libraries Used


- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [ML Kit OCR](https://developers.google.com/ml-kit/vision/text-recognition)
- [PdfBox-Android](https://github.com/TomRoush/PdfBox-Android)
- [Android TextToSpeech](https://developer.android.com/reference/android/speech/tts/TextToSpeech)
- [Supabase](https://supabase.com/)
- [CameraX](https://developer.android.com/training/camerax)

---

## 📬 Contact

👤 **Mohit Anuragi**  

📧 anuragimohit468@gmail.com  

🔗 [LinkedIn](https://www.linkedin.com/in/mohit-a-52989b2b2) 

💻 [GitHub](https://github.com/MohitAnuragi)
