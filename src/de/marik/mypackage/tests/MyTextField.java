package de.marik.mypackage.tests;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

class MyTextField extends JTextField {
	private static final long serialVersionUID = 6195103434485017619L;
	
	boolean validDataEntered = false;
    // Constructors
    public MyTextField () {
        super();
        init();
    }
    public MyTextField (String text) {
        super(text);
        init();
    }
    // Private initialization routine to be run at construct time
    private void init() {
        // add a key event listener that will consume tab keys until valid data entered in field
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                // look for tab keys
                if(event.getKeyCode() == KeyEvent.VK_TAB
                || event.getKeyChar() == '\t') {
                    // if no valid data entered in field, consume event
                    // so that it won't be passed on to focus manager
                    if(!validDataEntered) {
                        event.consume();
                    }
                }
                else {
                    // assume any key other than tab is valid data
                    validDataEntered = true;
                }
            }
        });
    }
    // Override to inform focus manager that component is managing focus changes
    public boolean isManagingFocus() { return true; }
}