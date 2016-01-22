0、原理：正常地，Class被加载时会为其打上CLASS_ISPREVERIFIED 标记 ,利用这点
将com.dhair.hotfix.Hack 给类构造方法种加入
import com.dhair.hotfix.Hack
if (ClassVerifier.PREVENT_VERIFY) {
System.out.println(Hack.class);
}
这样就防止类打上Class被加载时会为其打上CLASS_ISPREVERIFIED 标记
也为了防止报java.lang.IllegalAccessError: Class ref in pre-verified class resolved to unexpected implementation  异常

同时通过将自定义ClassLoader的顺序放入最前面，这样就可以把修复好的代码放在sdcard中被动态读入

0、App需要混淆
1、Build -> Clean Project
2、Run project
3、备份 app/build/outputs/dhair 至 Hotfix/hotfix/dhair
4、执行
./gradlew clean dhairQihooDebugPatch -P DhairDir=/Users/dengshengjin/ApplicationFiles/DeveloperFiles/DeveloperMyself/DeveloperDhair/DhairCostin/DhairCostin/Hotfix/hotfix/dhair

原理：将改动过的代码 .java  文件 编译后 与存在bug 的apk包生成的hash.txt和map.txt 文件做对比
以此生成差异的jar包，并利用android environment 生成差异的jar

