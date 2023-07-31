/*
 * Copyright (C) 2023 by Stefan Rothe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY); without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.kinet.pdf;

import com.itextpdf.layout.property.TextAlignment;

public final class Paragraph {

    final com.itextpdf.layout.element.Paragraph imp;

    Paragraph(String text) {
        this.imp = new com.itextpdf.layout.element.Paragraph(text);
    }

    public void setBold() {
        imp.setBold();
    }

    public void setFixedPosition(float left, float bottom, float width) {
        left = Document.cmToPoint(left);
        bottom = Document.cmToPoint(bottom);
        width = Document.cmToPoint(width);
        imp.setFixedPosition(left, bottom, width);
    }

    public void setFontSize(float fontSize) {
        imp.setFontSize(fontSize);
    }

    public void setTextAlignment(Alignment alignment) {
        imp.setTextAlignment(translate(alignment));
    }

    private TextAlignment translate(Alignment align) {
        switch (align) {
            case Center:
                return TextAlignment.CENTER;
            case Left:
                return TextAlignment.LEFT;
            case Right:
                return TextAlignment.RIGHT;
            default:
                return TextAlignment.LEFT;
        }
    }
}
