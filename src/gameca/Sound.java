/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author gmcoa
 */
public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];
    
    public Sound(){
        
        //fetch the sound file and store it on a variable
        soundURL[0] = getClass().getResource("/sound/Bat_Country.wav");
        soundURL[1] = getClass().getResource("/sound/Walk_Grass.wav");
        
    }
    
    public void setFile(int i){
        
        try{ //A format in Java to open an audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        }catch(Exception e){
            
        }
        
    }
    
    public void play(){
        
        clip.start();
        
    }
    
    public void loop(){
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    
    public void stop(){
        
        clip.stop();
        
    }
}
