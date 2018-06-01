# Directory Structure

```bash
├── app
│   ├── manifests
│   │   └── AndroidManifests.xml
│   ├── java
│   │   ├── com.mrinalraj.flipit
│   │   │   ├── Adapters
│   │   │   │   ├── EasyLevelAdapter.java
│   │   │   │   └── HardLevelAdapter.java
│   │   │   ├── Constants.java
│   │   │   ├── EasyLevel.java
│   │   │   ├── HardLevel.java
│   │   │   ├── Home.java
│   │   │   ├── Leaderboard.java
│   │   │   ├── Result.java
│   │   │   ├── SoundPlayer.java
│   │   │   └── Start.java
│   ├── assets
│   │   └── winner.mp3
│   └── res
│   │   ├── drawables
│   │   │   ├── card1.png
│   │   │   ├── card2.png
│   │   │   ├── card3.png
│   │   │   ├── card4.png
│   │   │   ├── card5.png
│   │   │   ├── card6.png
│   │   │   ├── card7.png
│   │   │   ├── card8.png
│   │   │   └── cardback.png
│   │   ├── layout
│   │   │   ├── activity_home.xml
│   │   │   ├── card.xml
│   │   │   ├── fragment_easy_level.xml
│   │   │   ├── fragment_hard_level.xml
│   │   │   ├── fragment_leaderboard.xml
│   │   │   ├── fragment_result.xml
│   │   │   ├── fragment_start.xml
│   │   │   ├── start_page_lower_flip_back.xml
│   │   │   └── start_page_lower_flip_front.xml
│   │   ├── mipmap
│   │   │   ├── ic_launcher
│   │   │   └── ic_launcher_round
│   │   └── values
│   │   │   ├── colors.xml
│   │   │   ├── strings.xml
│   │   │   └── styles.xml
├── Gradle Scripts
│   ├── build.gradle (app)
│   └── build.gradle (project)
├── README.md
└── .gitignore
```

---

# Card flipping memory game built for android

dependencies used 

```gradle
'com.wajahatkarim3.EasyFlipView:EasyFlipView:2.0.5'
'nl.dionsegijn:konfetti:1.1.0'

```

----

## Screenshots

![flip-it wireframe screenshots](https://raw.githubusercontent.com/mrinalraj/FlipIt/master/git-assets/flip-wireframe.png)