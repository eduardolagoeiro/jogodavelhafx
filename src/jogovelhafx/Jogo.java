/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelhafx;

/**
 *
 * @author lagoeiro
 */
public class Jogo {

    private Quadrinho[] quadro = new Quadrinho[9];

    public Quadrinho[] getQuadro() {
        return quadro;
    }

    public Jogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.quadro[i * 3 + j] = new Quadrinho();
            }
        }
        printaJogo();
    }

    public boolean checkWin() {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            result |= checkHorizontal(i);
            result |= checkVertical(i);
        }
        result |= checkDiagonal();
        result |= checkDiagonalOposta();
        return result;
    }

    private boolean checkHorizontal(int i) {
        byte s1 = this.quadro[i * 3 + 0].getValor();
        byte s2 = this.quadro[i * 3 + 1].getValor();
        byte s3 = this.quadro[i * 3 + 2].getValor();
        if (s1 != 2 && s2 != 2 && s3 != 2) {
            return s1 == s2 && s2 == s3;
        }
        return false;
    }

    private boolean checkVertical(int j) {
        byte s1 = this.quadro[0 * 3 + j].getValor();
        byte s2 = this.quadro[1 * 3 + j].getValor();
        byte s3 = this.quadro[2 * 3 + j].getValor();

        if (s1 != 2 && s2 != 2 && s3 != 2) {
            return s1 == s2 && s2 == s3;
        }
        return false;
    }

    private boolean checkDiagonal() {
        byte s1 = this.quadro[0 * 3 + 0].getValor();
        byte s2 = this.quadro[1 * 3 + 1].getValor();
        byte s3 = this.quadro[2 * 3 + 2].getValor();

        if (s1 != 2 && s2 != 2 && s3 != 2) {
            return s1 == s2 && s2 == s3;
        }
        return false;
    }

    private boolean checkDiagonalOposta() {
        byte s1 = this.quadro[2 * 3 + 0].getValor();
        byte s2 = this.quadro[1 * 3 + 1].getValor();
        byte s3 = this.quadro[0 * 3 + 2].getValor();

        if (s1 != 2 && s2 != 2 && s3 != 2) {
            return s1 == s2 && s2 == s3;
        }
        return false;
    }

    public void printaJogo() {
        System.out.println("=====");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.quadro[i * 3 + j].getValor()+"|");
            }
            System.out.println();
        }
    }
}
