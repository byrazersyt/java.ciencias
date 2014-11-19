
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Armand
 */
public class Tablero extends JFrame {
    private JLabel escaques[][];
    private final byte TAMANIO = 8;
    private final ImageIcon imagenCaballo;
    
    JMenuItem configuracion1;
    JMenuItem configuracion2;
    JMenuItem configuracion3;
    
    public Tablero() {
        super("Mi ajedrez");
        //Crea una imagen a partir de la imagen "imagenes/caballo.png" escalándola
        //a un tamaño de 50x50 px
        Image img = (new ImageIcon(getClass().getResource("imagenes/caballo.png"))).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        //Se crea un ImageIcon a partir de la imagen de arriba
        imagenCaballo = new ImageIcon(img);
        inicializarComponentes();//Inicializa los componentes que contendrá el JFrame
        establecerConfiguracionDelFrame();
    }
    
    private void inicializarComponentes() {
        //Se crea una barra de menús y el primer menú que estará en ella
        JMenuBar barraMenus = new JMenuBar();
        JMenu menuConfiguraiones = new JMenu("Configuraciones");
        
        //Se crea manejador de acciones que estará a la escucha de eventos de acción
        ManejadorConfiguraciones manejador = new ManejadorConfiguraciones();
        
        //Se crean 3 JMenuItem
        configuracion1 = new JMenuItem("Configuracion 1");
        configuracion2 = new JMenuItem("Configuracion 2");
        configuracion3 = new JMenuItem("Configuracion 3");
        
        //Se agrega un escucha de acciones a cada JMenuItem
        configuracion1.addActionListener(manejador);
        configuracion2.addActionListener(manejador);
        configuracion3.addActionListener(manejador);
        
        //Se agregan los JMenuItem al JMenu
        menuConfiguraiones.add(configuracion1);
        menuConfiguraiones.add(configuracion2);
        menuConfiguraiones.add(configuracion3);
        barraMenus.add(menuConfiguraiones);
        add(barraMenus, BorderLayout.NORTH);
        
        //Se crea un contenedor para acomodar los escaques
        JPanel contenedorEscaques = new JPanel(new GridLayout(8, 8));
        
        //Se crean los escaques como etiquetas y se establece un color a cada uno
        //posteriormente se añaden al contenedor
        boolean colorNegro = false;
        escaques = new JLabel[TAMANIO][TAMANIO];
        for(int i = 0; i < escaques.length; i++){
            for(int j = 0; j < escaques[i].length; j++){
                escaques[i][j] = new JLabel();
                escaques[i][j].setOpaque(true);
                if(colorNegro) escaques[i][j].setBackground(Color.black);
                else escaques[i][j].setBackground(Color.white);
                colorNegro = !colorNegro;
                contenedorEscaques.add(escaques[i][j]);
                
//                escaques[i][j].a
                        //addActionListener(manejador);

            }
            colorNegro = !colorNegro;
        }
        
        //Se agrega el contenedor de escaques al contenedor principal en la parte central
        add(contenedorEscaques);
    } //fin del método inicializarComponentes
    
    private void establecerConfiguracionDelFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(501, 527);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String [] argv){
        Tablero tablero = new Tablero();
    }
    
    private class ManejadorConfiguraciones implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {
            //Establece cada Icon de cada escaque en null (no hay imagenes)
            for(int i = 0; i < escaques.length; i++){
                for(int j = 0; j < escaques[i].length; j++){
                    escaques[i][j].setIcon(null);
                }
            }
            //Verifica dónde se dió el evento y dependiendo de ello se establece una
            //configuración de tablero
            if(evento.getSource() == configuracion1){
                escaques[4][5].setIcon(imagenCaballo);
            }
            else if(evento.getSource() == configuracion2){
                escaques[1][5].setIcon(imagenCaballo);
                escaques[6][7].setIcon(imagenCaballo);
            }
            else if(evento.getSource() == configuracion3){
                escaques[4][5].setIcon(imagenCaballo);
                escaques[3][5].setIcon(imagenCaballo);
                escaques[0][3].setIcon(imagenCaballo);
            }
            
            else {
                escaques[4][5].setIcon(imagenCaballo);
                escaques[3][5].setIcon(imagenCaballo);
                escaques[0][3].setIcon(imagenCaballo);
            }

            
        }
        
    } //fin de la clase interna ManejadorConfiguraciones
    
} //fin de la clase Tablero