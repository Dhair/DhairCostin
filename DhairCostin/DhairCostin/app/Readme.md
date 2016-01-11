(1)设置 android:configChanges="orientation" 和不设置这个属性，生命周期表现为重新创建activity

(2)设置 android:configChanges="orientation|keyboardHidden"，在2.3上表现为不重新创建activity，4.0如下

　　a)android:targetSdkVersion<="12"，生命周期表现为不重新创建activity

　　b)android:targetSdkVersion>"12"，表现为重新创建activity

(3)设置 android:configChanges="orientation|keyboardHidden|screenSize"，在2.3和4.0上都表现为不重新创建
