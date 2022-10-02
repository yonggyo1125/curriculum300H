# 카메라로 사진 찍어 저장하기

- 멀티미디어 기능에서 가장 기본적인 기능을 꼽는다면 카메라 기능이라고 할 수 있습니다. 카메라는 단순히 사진을 찍는 용도로 사용되지만 최근에는 카메라 미리보기 화면에 여러 가지 정보를 더 표시하거나 카메라로 보는 영상이나 이미지를 앱의 다른 기능에 활용하는 경우도 많습니다. 예를 들어, 카메라로 바코드를 찍으면 바코드 정보를 추출할 수 있는 바코드 리더기(Barcode Reader)를 만들 수 있습니다. 또는 카메라 미리보기 기능으로 주변에 있는 커피숍 정보나 매장의 쿠폰 정보를 함께 보여주는 증강현실(AR:Augmented Reality) 앱은 단순한 흥미 이상의 유용한 생활밀착형 기능을 제공합니다. 이렇게 다양한 목적으로 사용되는 카메라 기능에 익숙해지려면 가장 단순한 사용 방법을 먼저 알아보는것이 좋습니다.

- 카메라로 사진을 찍기 위해 사용되는 방법은 크게 두 가지로 나눌 수 있습니다.
	- 인텐트로 단말의 카메라 앱을 실행한 후 결과 사진을 받아 처리하기
	- 앱 화면에 카메라 미리보기를 보여주고 직접 사진을 찍어 처리하기
- 스마트폰 단말에는 카메라 앱이 미리 설치되어 있는데 이 앱을 사용하면 가장 간단하게 다른 기능의 앱을 구현할 수 있습니다. 단말의 카메라 앱은 다른 개발자가 미리 만들어 설치해둔 것이므로 여러분이 만들려는 앱에서 카메라 앱의 화면을 띄우려면 인텐트를 만들어 시스템에 요청하면 됩니다.

- 지금부터 인텐트를 사용해서 단말의 카메라 앱을 실행한 후 결과 사진을 받아 처리하는 방법을 알아보겠습니다. 이번에 만들 앱은 사용자가 화면의 버튼을 클릭했을 때 카메라 앱의 화면을 띄웁니다. 그리고 카메라 앱으로 사진을 찍은 후 원래 화면으로 돌아오면 찍은 사진을 화면에 보여 주게 할 것입니다. SampleCaptureIntent라는 새 프로젝트를 만들고 패키지 이름은 org.koreait.captureintent로 입력합니다. 프로젝트 창이 뜨면 activity_main.xml 파일을 열고 다음과 같은 화면을 만듭니다.

#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="사진찍기" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:srcCompat="@mipmap/ic_launcher" />
    
</LinearLayout>
```



- ﻿디자인 화면에서 최상위 레이아웃을 LinearLayout으로 변경하고 orientation 속성 값을 vertical로 설정합니다. 버튼을 하나 추가하고 '사진 찍기'라는 글자가 보이게 만든 후 화면 상단의 가운데 위치하도록 합니다. 버튼의 아래쪽에는 이미지뷰를 추가하고 아래쪽 공간을 꽉 채우세요. 화면을 만들었다면 MainActivity.java 파일을 열고 소스 코드를 입력합니다. 화면에 있는 버튼을 클릭했을 때 단말의 카메라 앱을 띄우도록 하고 카메라 앱에서 사진을 찍고 돌아왔을 때 이미지뷰에 사진을 보여주도록 할 것입니다.

#### SampleCapturelntent>/app/java/org.koreait.capture.intent/MainActivity.java

```java

```







