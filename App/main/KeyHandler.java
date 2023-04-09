package App.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
    AppPanel ap;

    public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed;
    
    //debug
    boolean checkDrawTime=false;

    public KeyHandler(AppPanel ap) {
        this.ap=ap;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();

        //title state
        if(ap.gameState==ap.titleState) {
            if(ap.ui.titleScreenState==0) {
                if(code==KeyEvent.VK_UP) {
                    ap.ui.commandNum--;
                    if(ap.ui.commandNum<0) {
                        ap.ui.commandNum=2;
                    }
                }
                if(code==KeyEvent.VK_DOWN) {
                    ap.ui.commandNum++;
                    if(ap.ui.commandNum>2) {
                        ap.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER) {
                    if(ap.ui.commandNum==0) {
                        //move to character selection screen
                        ap.ui.titleScreenState=1;
                    }
                    if(ap.ui.commandNum==1) {
                        
                    }
                    if(ap.ui.commandNum==2) {
                        System.exit(0);
                    }
                }
            }
            else if(ap.ui.titleScreenState==1) {
                if(code==KeyEvent.VK_UP) {
                    ap.ui.commandNum--;
                    if(ap.ui.commandNum<0) {
                        ap.ui.commandNum=2;
                    }
                }
                if(code==KeyEvent.VK_DOWN) {
                    ap.ui.commandNum++;
                    if(ap.ui.commandNum>2) {
                        ap.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER) {
                    if(ap.ui.commandNum==0) {
                        System.out.println("female selected!");
                        ap.gameState=ap.selectCancerState; //moving to next state!
                        ap.playMusic(0);
                    }
                    if(ap.ui.commandNum==1) {
                        System.out.println("male selected!"); 
                        ap.gameState=ap.selectCancerState; //moving to next state!
                    }
                    // if(ap.ui.commandNum==2) {
                    //     System.out.println("some sorcerer specific stuff!");
                    //     ap.gameState=ap.playState;
                    // }
                    if(ap.ui.commandNum==2) {
                        ap.ui.titleScreenState=0; //"back" was selected
                    }
                }
            }
        }

        //play state
        if(ap.gameState==ap.playState) {
            if(code==KeyEvent.VK_UP) {
                upPressed=true;
            }
            if(code==KeyEvent.VK_DOWN) {
                downPressed=true;
            }
            if(code==KeyEvent.VK_LEFT) {
                leftPressed=true;
            }
            if(code==KeyEvent.VK_RIGHT) {
                rightPressed=true;
            }
            if(code==KeyEvent.VK_P) {
                ap.gameState=ap.pauseState;
            }
            if(code==KeyEvent.VK_ENTER) {
                enterPressed=true;
            }
            //debug
            if(code==KeyEvent.VK_T) {
                if(!checkDrawTime) {
                    checkDrawTime=true;
                }
                else{
                    checkDrawTime=false;
                }
            }
        }
        //pause state
        else if(ap.gameState==ap.pauseState) {
            if(code==KeyEvent.VK_P) {
                ap.gameState=ap.playState;
            }
        }
        //dialogue state
        else if(ap.gameState==ap.dialogueState) {
            if(code==KeyEvent.VK_ENTER) {
                ap.gameState=ap.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP) {
            upPressed=false;
        }
        if(code==KeyEvent.VK_DOWN) {
            downPressed=false;
        }
        if(code==KeyEvent.VK_LEFT) {
            leftPressed=false;
        }
        if(code==KeyEvent.VK_RIGHT) {
            rightPressed=false;
        }
    }
}
