package versionChecker.Util;

import org.jsoup.Jsoup;

public class VerifyUpdate {

    UpdateListener updateListener;

    public interface UpdateListener {
        void updateExisting(boolean result);
    }

    public void verifyUpdate(UpdateListener updateListener) {
        this.updateListener = updateListener;
        new GetVersionCode().run();
    }

    private class GetVersionCode implements Runnable {
        String currentVersion;

        @Override
        public void run() {
            String newVersion = null;
            try {
                newVersion = Jsoup.connect("https://appgallery.huawei.com/app/C102733457")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select(".detailInfo > div:nth-child(1) > div:nth-child(3) > div:nth-child(2)").text();
                        //.get(7)
                        //.ownText();
                System.out.println(newVersion);
            } catch (Exception e) {
                System.out.println(newVersion);
                e.printStackTrace();
            }
        }
    }

    private void updateCheckin(String install, String playstore) {
        String[] versionInstallArray = install.split("\\.");
        String[] versionOnlineArray = playstore.split("\\.");
        if (versionInstallArray.length == 3 && versionOnlineArray.length == 3) {
            int valu1Install = Integer.valueOf(versionInstallArray[0]);
            int valu1Online = Integer.valueOf(versionOnlineArray[0]);

            int valu2Install = Integer.valueOf(versionInstallArray[1]);
            int valu2Online = Integer.valueOf(versionOnlineArray[1]);

            int valu3Install = Integer.valueOf(versionInstallArray[2]);
            int valu3Online = Integer.valueOf(versionOnlineArray[2]);

            if(valu1Install > valu1Online)
                updateListener.updateExisting(valu1Install >= valu1Online);
            else if(valu2Install > valu2Online)
                updateListener.updateExisting(valu1Install == valu1Online && valu2Install >= valu2Online);
            else updateListener.updateExisting(valu1Install == valu1Online && valu2Install == valu2Online && valu3Install >= valu3Online);
        } else updateListener.updateExisting(false);

    }
}
