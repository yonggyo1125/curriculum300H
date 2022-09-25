# 리소스와 매니페스트 이해하기

- 안드로이드 앱은 크게 '자바 코드'와 '리소스'로 구성됩니다. 자바 코드에서는 앱의 흐름과 기능을 정의하고 리소스에서는 레이아웃이나 이미지처럼 사용자에게 보여주기 위해 사용하는 파일이나 데이터를 관리합니다. 지금까지 XML 레이아웃이나 이미지를 보여줄 때 사용해 보았으므로 res 폴더 안에 들어있는 리소스가 낯설지는 않을 것입니다. 

## 매니페스트

- 매니페스트가 리소스는 아니지만 설치된 앱의 구성 요소가 어떤 것인지, 그리고 어떤 권한이 부여되었는지 시스템에 알려주기 때문에 매우 중요합니다. 모든 안드로이드 앱은 가장 상위 폴더에 매니페스트 파일이 있어야 하며, 이 정보는 앱이 실행되기 전에 시스템이 알아야 할 내용들을 정의하고 있습니다.다음은 매니페스트 파일에 들어갈 수 있는 태그 항목들을 나열하고 있습니다.

```
<action>
<activity>
<activity-alias>
<application>
<category>
<data>
<grant-uri-permission>

<instrumentation>
<intent-filter>
<manifest>
<meta-data>
<permission>
<permission-group>
<permission-tree>

<provider>
<receiver>
<service>
<uses-configuration>
<uses-library>
<uses-permission>
<uses-sdk>
```

- 이 태그들 중에서 \<activity\>, \<service\>, \<receiver\>, \<provider\>와 같은 태그들은 앱 구성 요소를 등록하기 위해 사용되며, \<activity\> 태그는 여러분이 이미 여러 번 사용해 보았습니다. 이를 포함한 매니페스트의 주요 역할들을 살펴보면 다음과 같습니다.
	- 앱의 패키지 이름 지정
	- 앱 구성 요소에 대한 정보 등록(액티비티, 서비스, 브로드캐스트 수신자, 내용 제공자)
	- 각 구성 요소를 구현하는 클래스 이름 지정
	- 앱이 가져야 하는 권한에 대한 정보 등록
	- 다른 앱이 접근하기 위해 필요한 권한에 대한 정보 등록
	- 앱 개발 과정에서 프로파일링을 위해 필요한 instrumentation 클래스 등록 
	- 앱에 필요한 안드로이드 API의 레벨 정보 등록
	- 앱에서 사용하는 라이브러리 리스트
	

- ﻿다음은 매니페스트 파일의 기본 구조를 보여줍니다

```xml
<manifest ... >
	<application ... >
		... 
		
		<service android:mame="org.koreait.service.MyService" ... >
			...
		</service>
		... 
	</application>
</mainfest>
```

- 매니페스트 파일에는 타이틀이나 아이콘과 같은 앱 자체의 정보를 속성으로 지정할 수 있으며, 이미지리소스로 포함된 정보들은 "@drawable/..."과 같이 참조하여 지정할 수 있습니다. 이때 애플리케이션을 의미하는 \<application\> 태그는 매니페스트 안에 반드시 하나만 있어야 합니다. 나머지 \<application\> 태그 안의 구성 요소들은 같은 태그가 여러 번 추가되어도 괜찮습니다.

- 그중에서 메인 액티비티는 항상 다음과 같은 형태로 추가되어야 합니다. 즉, 인텐트 필터에 들어가는 정보는 \<action\> 태그의 경우 MAIN이 되어야 하고 \<category\> 태그의 경우 LAUNCHER가 되어야 합니다.

```xml
<activity android:name="org.koreait.hello.HelloActivity" 
	android:label="@string/app_name">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
```

## 리소스의 사용

- 리소스를 자바 코드와 분리하는 이유는 이해하기 쉽고 유지관리가 용이하기 때문입니다. 프로젝트를 처음 만든 후에는 /app/res 폴더 이외에 /app/assets 폴더를 따로 만들 수 있는데 두 가지 모두 리소스라고 할 수 있으며 대부분은 /app/res 폴더 밑에서 관리됩니다. 두 가지 데이터의 차이점은 다음과 같습니다.
	- 애셋(Asset)은 동영상이나 웹페이지와 같이 용량이 큰 데이터를 의미합니다.
	- 리소스는 빌드되어 설치 파일에 추가되지만 애셋은 빌드되지 않습니다.

- ﻿리소스는 /app/res 폴더 밑에 있는 여러 가지 폴더에 나누어 저장되며 리소스 유형별로 서로 다른 폴더에 저장합니다. 프로젝트를 처음 만들면 몇 개의 폴더만 들어 있는데 실제 만들 수 있는 폴더는 훨씬 많아서 필요한 경우에 만들어 사용해야 합니다. 리소스가 갱신되면 그때마다 리소스의 정보가 R.java 파일에 자동으로 기록되며 그 정보는 리소스에 대한 내부적인 포인터 정보가 됩니다.

> 리소스는 그 유형에 따라 정해진 폴더 안에 넣어야 합니다. 이렇게 리소스가 유형별로 서로 다른 폴더에서 관리되면 리소스별로 구분하기 쉽고 유지관리가 편리하다는 장점이 생깁니다.

- /app/res/values 폴더에는 문자열이나 기타 기본 데이터 타입에 해당하는 정보들이 저장됩니다. 예를 들어, strings.xml 파일에는 문자열을 저장합니다. 만약 다른 이름의 파일을 만들어 저장하는 경우 그 안에는 XML 포맷에 맞는 데이터가 들어가 있어야 합니다.

- /app/res/drawable 폴더에는 이미지를 저장합니다. 이 폴더는 단말의 해상도에 따라 다른 이미지를 보여줄 수 있도록 /app/res/drawable-xhdpi, /app/res/drawable-hdpi, /app/res/drawable-mdpi 등으로 나누어 저장할 수 있습니다. 그러면 각 단말에서 해상도에 맞는 폴더를 찾아 그 안에 들어있는 이미지를 참조하게 됩니다. 이렇게 저장되어 있는 리소스 정보를 코드에서 사용할 때에는 Resources 객체를 참조하여 리소스를 읽어 들여야 합니다. Resources 객체는 Context.getResources() 메서드를 이용해 액티비티 안에서 언제든지 참조할 수 있습니다. 이 객체에는 리소스의 유형에 따라 읽어 들일 수 있는 메서드가 정의되어 있어 필요에 따라 사용할 수 있습니다. 각 리소스 유형별 사용 방식은 앞으로 나올 예제들에서 하나씩 접해볼 수 있을 것입니다.

## 스타일과 테마

- 스타일과 테마는 여러 가지 속성들을 한꺼번에 모아서 정의한 것으로 가장 대표적인 예로 대화상자를 들 수 있습니다. 대화상자의 경우에는 액티비티와 달리 타이틀 부분이나 모서리 부분의 형태가 약간 다르게 보이는데 이런 속성들을 다이얼로그(Dialog) 테마로 정의하여 액티비티에 적용하면 대화상자 모양으로 보이게 됩니다. 물론 안드로이드에서는 자주 사용되는 스타일과 테마를 제공하긴 하지만 필요에 따라 직접 정의해서 사용해야 합니다. 만약 스타일을 직접 정의하여 사용하고 싶다면 /app/res/values/themes.xml 파일에 추가해야 합니다.

* * * 
# 그래들 이해하기

- 안드로이드 앱을 실행하거나 앱 스토어에 올릴 때는 소스 파일이나 리소스 파일을 빌드하거나 배포하는 작업이 필요합니다. 이때 사용되는 것이 그래들(Gradle)입니다. 다시 말해, 그래들은 안드로이드 스튜디오에서 사용하는 빌드 및 배포 도구인 것입니다.

- 한 앱의 빌드 설정은 build.gradle 파일에 넣어 관리합니다. 이때 그래들 파일은 프로젝트 수준과 모듈 수준으로 나눠 관리하기 때문에새로운 프로젝트를 만들면 두 개의 build.gradle 파일이 생깁니다. 

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/7.%20%EB%A6%AC%EC%86%8C%EC%8A%A4%EC%99%80%20%EB%A7%A4%EB%8B%88%ED%8E%98%EC%9D%B4%EC%8A%A4%2C%20%EA%B7%B8%EB%9E%98%EB%93%A4%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image1.png)

- 그러면 앞에서 만들었던 SamplePermission2 프로젝트의 그래들 설정 파일을 살펴보겠습니다. 왼쪽에서 Gradle Scripts를 펼쳐보세요. 먼저 프로젝트 수준의 그래들 설정파일의 이름은 build.gradle (Project: SamplePermission2) 파일입니다


#### SamplePermission2>/Gradle Scripts/build.gradle (Project: SamplePermission2)

```
plugins {
    id 'com.android.application' version '7.2.2' apply false
    id 'com.android.library' version '7.2.2' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

- 이 파일은 프로젝트 안에 들어있는 모든 모듈에 적용되는 설정을 담고 있습니다. 이 파일을 수정하는 경우는 거의 없습니다. 
- 다음은 모듈 수준의 그래들 설정 파일입니다. 파일의 이름은 build.gradle(Module: SamplePermission2.app) 입니다.

#### SamplePermission2>/Gradle Scripts/build.gradle (Module: SamplePermission2)

```
plugins {
    id 'com.android.application'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "org.koreait.permission2"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation 'com.yanzhenjie:permission:2.0.2'
}
```

- 이 파일은 각각의 모듈에 대한 설정을 담고 있습니다. 프로젝트가 만들어지면 app 모듈은 기본으로 만들어지는데, 이 파일이 app 모듈의 설정 정보를 담고 있는 것입니다. 만약 새로운 모듈을 추가한다면 그 모듈에 대한 build.gradle 파일도 새로 추가됩니다. 이 파일에는 빌드에 필요한 중요한 정보들이 들어 있습니다. 여러분이 꼭 살펴보면 좋을 모듈 정보에 대해서 설명하겠습니다.

- applicationld는 이 앱의 id 값입니다. 여러분이 만든 앱은 id 값으로 구분되기 때문에 id 값은 전 세계에서 유일한 값으로 설정되어야 합니다. compileSdkVersion은 빌드를 진행할 때 어떤 버전의 SDK를 사용할 것인지를 지정합니다. 보통 최신 버전의 SDK 버전을 지정하게 됩니다. minSdkVersion은 이앱이 어떤 하위 버전까지 지원하도록 할 것인지를 지정합니다. 모든 단말을 지원하면 좋겠지만 보통 앱에서 사용하는 최신 기능을 하위 단말에서 지원하지 못하는 경우에는 앱에서 사용하는 기능을 지원하기 시작한 버전을 minSdkVersion으로 지정하게 됩니다. targetSdkVersion은 이 앱이 검증된 버전이 어떤 SDK 버전인지를 지정합니다. 만약 새로운 SDK가 출시되었다고 하더라도 해당 SDK에서 검증되지 않은 앱은 이 버전을 이전 버전으로 지정할 수도 있습니다. dependencies에는 외부 라이브러리를 추가할 수 있습니다. 위의 기본 설정을 사용하면 libs 폴더 안에 들어있는 jar 파일을 읽어 들이고 support 패키지를 추가합니다. 가장 마지막 줄에 있는 implementation으로 시작하는 한 줄은 여러분이 직접 추가한 외부 라이브러리입니다.

- 마지막으로 settings.gradle 파일에는 프로젝트의 이름과 함께 어떤 모듈을 포함할 것인지에 대한 정보가 들어 있습니다.

#### SamplePermission2>/Gradle Scripts/settings.gradle

```
rootProject.name = "SamplePermission2"
include ':app'
```

- 이 내용은 안드로이드 스튜디오에서 어떻게 설정하는가에 따라 자동으로 변경될 수 있습니다. 이 외에 local.properties 파일 안에는 현재 사용하고 있는 PC에 설치된 SDK의 위치가 기록되어 있으며 gradle.properties 파일 안에는 메모리 설정이 들어있습니다. 그리고 gradle-wrapper.properties 파일에는 그래들 버전 정보 등이 들어 있는데, 이런 정보들은 안드로이드 스튜디오에서 자동 설정하는 경우가 많아 여러분이 굳이 기억하지 않아도 됩니다.