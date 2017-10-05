/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelhafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author lagoeiro
 */
public class Quadrinho {

    private byte valor = 2;

    public byte getValor() {
        return valor;
    }
    
    public boolean isNotMarcado() {
        return valor == 2;
    }

    public void setNull(ImageView imgView) {
        imgView.setImage(new Image("/jogovelhafx/img/null.png"));
        valor = 2;
    }

    public void setX(ImageView imgView) {
        imgView.setImage(new Image("/jogovelhafx/img/x.png"));
        valor = 1;
    }

    public void setO(ImageView imgView) {
        imgView.setImage(new Image("/jogovelhafx/img/o.png"));
        valor = 0;
    }

}
