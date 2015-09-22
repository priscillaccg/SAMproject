/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

/**
 *
 * @author Priscilla
 */
public class SingletonUtil {
    private static final long serialVersionUID = 1L;
    private static SingletonUtil me;
    private Usuario usuario;

    private SingletonUtil(){
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static SingletonUtil getInstance() {
        if (me== null){
            me = new SingletonUtil();
        }
        return me;
    }
}
