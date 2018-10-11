package Other;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Music extends Father{
	public Music(){

	}


	/**
	 * 创建音乐对象
	 *
	 * @param music
	 *            音乐的路径
	 * @return AudioClip对象
	 */
	public AudioClip music(String music) {
		AudioClip auu = null;
		try {
			URL cb;
			File f = new File(music);
			cb = f.toURL();
			auu = Applet.newAudioClip(cb);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return auu;
	}
}
