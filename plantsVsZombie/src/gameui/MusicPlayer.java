package gameui;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer{

    private String filename;
    private boolean exit;
    private boolean interruptFlag;
    private int gapTime;
    public MusicPlayer(String wavfile,boolean exit,int gaptime) {
        filename = wavfile;
        this.exit = exit;
        gapTime = gaptime;
        interruptFlag = false;
    }
    public void musicTimer() {
    	Timer timer = new Timer();   	
    	TimerTask timertask = new TimerTask() {
    		File soundFile = new File(filename);
    		public void run() {   			
                // 获取音频输入流
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return;
                }
                // 获取音频编码对象
                AudioFormat format = audioInputStream.getFormat();
                // 设置数据输入
                SourceDataLine auline = null;
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                try {
                    auline = (SourceDataLine) AudioSystem.getLine(info);
                    auline.open(format);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                auline.start();
              /*
               * 从输入流中读取数据发送到混音器
               */
                int nBytesRead = 0;
                byte[] abData = new byte[512];

                try {
                    while (nBytesRead != -1&&interruptFlag==false) {
                        nBytesRead = audioInputStream.read(abData, 0, abData.length);
                        if (nBytesRead >= 0)
                            auline.write(abData, 0, nBytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    // 清空数据缓冲,并关闭输入
                    auline.drain();
                    auline.close();
                }
                if(exit) {
                	timer.cancel();
                }
    		}
    	};
    	timer.schedule(timertask,0,gapTime);
    }
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}
	
    public boolean isInterruptFlag() {
		return interruptFlag;
	}
	public void setInterruptFlag(boolean interruptFlag) {
		this.interruptFlag = interruptFlag;
	}
	public void endPlay() {
    	this.interruptFlag = true;
    }
}
