package de.bombshooter.bombshooter.ui;

import processing.core.PGraphics;
import processing.core.PVector;

public class Button extends UIElement {

        private String text;

        public Button(PVector position, PVector size) {
                super(position, size);
        }

        @Override
        protected void onDraw(PGraphics gfx) {
                gfx.fill(0);
                gfx.rect(50,50,200,100);
        }

        public String getText() {
                return text;
        }

        public Button setText(String text) {
                this.text = text;
                return this;
        }
}
