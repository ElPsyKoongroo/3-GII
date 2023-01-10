import Clases.AFD;
import Clases.AFND;

import Clases.MiCanvas;
import Clases.MiFrame;
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Graph.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import guru.nidi.graphviz.engine.Engine;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        MiFrame frame = new MiFrame();
        frame.setVisible(true);
        
        
        /*AFD afd = AFD.LeerAFD("");
        AFND afnd = AFND.LeerAFND("");


        String[] cadenas = new String[]{
                "aaa",
                "bbbb",
                "aaababa",
                "aaab",
                "bba",
                "a",
                "b",
                "ab"
        };

        int bien = 0;
        for(String cadena : cadenas){
            if(afnd.Match(cadena)) {
                System.out.println(cadena + ": Si");
                bien++;
            }
        }
        for(String cadena : cadenas){
            if(!afnd.Match(cadena)) {
                System.out.println(cadena + ": No");
            }
        }
        System.out.println("Bien: " + bien);
        System.out.println("Total: " + cadenas.length);*/

        /*Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class")
                .with(
                        node("a").with(Color.RED).link(node("b")),
                        node("b").link(
                                to(node("c"))
                                        .with(attr("label", "rgrggrgrHola")),
                                to(node("D"))
                        )
                );

        var esto2 = Graphviz.fromGraph(g).render(Format.PNG).toImage();

        Frame frame = new Frame();

        frame.setSize(800, 800);
        frame.setVisible(true);

        MiCanvas canvas = new MiCanvas(esto2);


        frame.add(canvas);*/


    }
}