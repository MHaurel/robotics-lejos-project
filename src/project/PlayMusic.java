package project;

import lejos.hardware.Sound;

public class PlayMusic {
	
	public static void playMusic(int colorId) throws InterruptedException {
		switch (colorId) {
		case 0:
			Sound.playTone(440, 100);
			Sound.playTone(523, 100);
			Sound.playTone(659, 100);
			break;
		case 1:
			Sound.playTone(392, 100);
			Sound.playTone(494, 100);
			Sound.playTone(587, 100);
			break;
		case 2:
			Sound.playTone(587, 100);
			Sound.playTone(689, 100);
			Sound.playTone(880, 100);
			break;
		case 3:
			Sound.playTone(349, 100);
			Sound.playTone(440, 100);
			Sound.playTone(523, 100);
			break;
		case 4:
			Sound.playTone(523, 100);
			Sound.playTone(659, 100);
			Sound.playTone(784, 100);
		}
			
	}

}
