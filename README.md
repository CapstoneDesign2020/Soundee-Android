# Soundee Android 

### 딥러닝을 이용한 일상생활 소리 분류 및 알림 애플리케이션

2020 덕성여자대학교 IT미디어공학과 캡스톤디자인 

 

#### 기획의도

사물 인터넷이 활성화되어 생활에 많은 부분들이 편리해졌지만, 농인은 이 편리함에 소외된 것이 현실이다. 소리를 듣지 못하는 농인들은 냉장고를 오래 열어놨을 시 발생하는 경보음, 잊어버리고 끄지 못한 드라이기, 물소리 등의 소리를 인지하지 못한다. 농인에게도 이를 감지하고 인식할 수 있는 시스템이 필요하다. 이를 해결하기 위해 본 프로젝트는 가정에서 발생하는 소리를 인지하여 분류하고 사용자의 패턴을 분석하여 현재 발생한 혹은 발생할 가능성이 있는 상황을 적절한 시점에 알려주는 애플리케이션을 기획하였다. 이를 통해 농인이 인지하지 못하는 위험과 낭비를 미리 예방하여, 농인에게 좀 더 안전하고 편리한 생활을 제공하고자 한다.

<br>

 

#### 작품소개

무지향성 마이크를 통해 일상 소리를 감지한다. 마이크로 수집한 음향 데이터를 서버에 송신하고, 서버는 수신한 소리를 딥러닝을 기반으로 한 음향 예측 알고리즘을 통해 예측 결과를 안드로이드 기기에 송신한다. 안드로이드 기기는 수신한 데이터를 푸시알림과 통계 등 다양한 방법으로 제공하여 청각장애인을 위한 서비스를 제공한다.

본 프로젝트로 사회가 생각해보지 못한 청각장애인의 일상생활의 불편함에 대한 문제를 제기하여 이에 대한 관심과 해결방안의 마련을 도모한다. 더불어 앞으로의 4차 산업 기술의 발전이 비장애인의 편리함뿐만이 아닌, 장애인의 접근성을 고려해야한다는 사회적 인식을 확산시키는데 도움이 될 것이라 예상한다.

<br>


## Soundee Application 구조도

<img src="https://user-images.githubusercontent.com/51378843/95902399-b91cdd00-0dcf-11eb-8a32-31523aa4a452.png" width="500" />

애플리케이션에서 5초마다 실시간 소리 정보를 요청하고 그에 대한 서버의 로직이다. 클라이언트는 해당 소리의 팝업 알림을 띄워 사용자에게 소리를 알려준다.

<br>


## Soundee Information Architecture
<img src="https://user-images.githubusercontent.com/51378843/95900073-570ea880-0dcc-11eb-8b2a-2b698044a84c.png" width="500" />

<br>
<br>

## UI



### Notification

<img src="https://user-images.githubusercontent.com/51378843/95900549-077cac80-0dcd-11eb-8c9e-122caf104f3b.png" width="200" />

pc에 연결되어 있는 마이크를 통해 아기 울음 소리, 모터 소리, 물 소리 등이 들리면 해당 소리에 대한 팝업 알림을 제공합니다.




### Home
<img src="https://user-images.githubusercontent.com/51378843/95900334-b2d93180-0dcc-11eb-8ee7-0c4733c2da64.png" width="600" />

팝업 알림을 누르면, 아기 울음 소리, 모터 소리, 물 소리 등의 해당 소리와 아기의 상태를 확인해주세요, 청소기나 드라이기 소리가 들리니 켜지지는 않았나 액션에 대해서 표시 해줍니다.



### Chart
<img src="https://user-images.githubusercontent.com/51378843/95900236-8f15eb80-0dcc-11eb-878c-2e08906d08c0.png" width="600" />

일일, 주간, 월간 으로 총 3개의 그래프로 한눈에 파악할 수 있습니다.
차트를 클릭한다면 해당 요일 또는 해당 월에 어떤 소리가 났는 세부적으로 보여줍니다. 그래프는 MPAndroidChart의 PieChart, BarChart, LineChart 를 사용하였습니다. 


### Setting & SignIn/Up
<img src="https://user-images.githubusercontent.com/51378843/95900341-b4a2f500-0dcc-11eb-9af2-7cf2143dd20e.png" width="700"/>
<br><br>


## 📚라이브러리

Chart - [MpAndroidChart](https://github.com/PhilJay/MPAndroidChart)

- PieChart

- BarChart

  - roundedbar로 변형 [RoundedBarChartRender.kt](https://github.com/CapstoneDesign2020/Soundee-Android/blob/master/app/src/main/java/com/soundee/soundee/chart/RoundedBarChartRenderer.kt) 

  - 클릭이벤트

    `chart_bar_weekly.setOnChartValueSelectedListener(object :            com.github.mikephil.charting.listener.OnChartValueSelectedListener {            override fun onNothingSelected() {                weeklyChartDetailsAdapter.notifyDataSetChanged()            }             override fun onValueSelected(e: Entry?, h: Highlight?) {                val entry = e?.x?.toInt() ?: 0                day = entry                notifyDataWeeklyChartDetails(day)             }        })`

- LineChart

  - 부드러운 곡선 

    ​	`lineDataSet.mode=LineDataSet.Mode.CUBIC_BEZIER`

  - 그리드 점선으로 만들기

    ​	`xAxis.enableGridDashedLine(20f,10f,0f)`

  - 클릭이벤트시 세로선 하이라이트 표시

    `lineDataSet.setDrawHorizontalHighlightIndicator(false) //수평선 사용 안함 lineDataSet.highLightColor=ContextCompat.getColor(context!!,R.color.colorPointGreen) lineDataSet.highlightLineWidth=2f`

  - 클릭이벤트 - MarkerView 사용
    - [markerview_monthly.xml](https://github.com/CapstoneDesign2020/Soundee-Android/blob/master/app/src/main/res/layout/markerview_monthly.xml)      
    - [MonthlyChartMarker.kt](https://github.com/CapstoneDesign2020/Soundee-Android/blob/master/app/src/main/java/com/soundee/soundee/chart/MonthlyChartMarker.kt)        
    
<br><br>
