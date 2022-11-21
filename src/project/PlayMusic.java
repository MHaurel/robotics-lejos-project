package project;

import lejos.hardware.Sound;

public class PlayMusic {
	
	public static void playMusic(int colorId) throws InterruptedException {
		
		Sound.setVolume(50);
		int duration = 100;
		
		switch (colorId) {
		case 0: //RED = Am
			Sound.playTone(440, duration); //A
			Sound.playTone(523, duration); //C
			Sound.playTone(659, duration); //E
			break;
		case 1: //GREEN = G
			Sound.playTone(392, duration); //G
			Sound.playTone(494, duration); //B
			Sound.playTone(587, duration); //D
			break;
		case 2: //BLUE = Dm
			Sound.playTone(587, duration); //D
			Sound.playTone(698, duration); //F
			Sound.playTone(880, duration); //A
			break;
		case 3: //ORANGE = F
			Sound.playTone(349, duration); //F
			Sound.playTone(440, duration); //A
			Sound.playTone(523, duration); //C
			break;
		case 4: //WHITE = C
			Sound.playTone(523, duration); //C
			Sound.playTone(659, duration); //E
			Sound.playTone(784, duration); //G
		}
			
	}

}
