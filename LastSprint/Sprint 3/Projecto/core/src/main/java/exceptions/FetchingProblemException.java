/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Grupo 3 - Turma 1
 */
public class FetchingProblemException extends IllegalArgumentException {
    public FetchingProblemException(String s) {
        super(s);
    }
}
