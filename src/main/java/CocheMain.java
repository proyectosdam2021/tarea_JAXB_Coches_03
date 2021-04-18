
import com.xml.jaxb.model.Coche;
import com.xml.jaxb.model.Concesionario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;

public class CocheMain {

    private static final String Concesionario_XML = "./concesionario-jaxb.xml";

    public static void main(String[] args) throws JAXBException, FileNotFoundException, IOException {

        //Mediante este método establecemos las propiedades para hacer funcionar el JSON **********IMPORTANTE INCLUIRLO***********
        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        //************************************************************************************************************************

        // Declaramos los objetos que vamos a usar para crear nuestra estructura XML
        var cocheList = new ArrayList<Coche>(); // Nodo padre

        var coche1 = new Coche();
        coche1.setMarca("Toyota");
        coche1.setModelo("CHR");
        coche1.setColor("Blanco Perla");
        cocheList.add(coche1);

        var coche2 = new Coche();
        coche2.setMarca("Nissan");
        coche2.setModelo("Quasqai");
        coche2.setColor("Azul");
        cocheList.add(coche2);

        var coche3 = new Coche();
        coche3.setMarca("Lancia");
        coche3.setModelo("Thema");
        coche3.setColor("Negro");
        cocheList.add(coche3);

        var concesionario = new Concesionario(); // Nodo raiz

        concesionario.setNombre("MiConcesionario");
        concesionario.setLocalizacion("Telde");
        concesionario.setCocheList(cocheList);

        // Creamos un JaxBContext
        JAXBContext contexto = JAXBContext.newInstance(Concesionario.class);
        // Creamos el objeto Marshaller usando el JaxB contexto
        Marshaller marshaller = contexto.createMarshaller();
        // Establecemos las propiedades para el XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        System.out.println("\n*************************");
        System.out.println("*  Grabando fichero XML *");
        System.out.println("*************************\n");
        // Damos salida a los datos del Concesionario
        marshaller.marshal(concesionario, System.out);
        // Creamos el fichero
        marshaller.marshal(concesionario, new File(Concesionario_XML));

        System.out.println("\n*************************");
        System.out.println("*  Leyendo  fichero XML *");
        System.out.println("*************************\n");

        // Creamos un Unmarshaller para el proceso de deserializacion de los datos XML
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        Concesionario concesionario2 = (Concesionario) unmarshaller.unmarshal(new FileReader(Concesionario_XML));

        List<Coche> list = concesionario2.getCochesList();
        System.out.println("Marca" + "\t\t" + "Modelo" + "\t\t" + "Color");
        System.out.println("-----" + "\t\t" + "------" + "\t\t" + "-----");
        for (Coche coche : list) {
            System.out.println(coche.getMarca() + "\t\t" + coche.getModelo() + "\t\t" + coche.getColor());
        }

        System.out.println("\n*************************");
        System.out.println("*  Mostrando fichero JSON *");
        System.out.println("*************************\n");

        //Llamamos al método grabarJSON
        grabarJSON(concesionario);

    }

    private static void grabarJSON(Concesionario concesionario) throws IOException {
        File file = new File("./coches.json");
        try {
            // Creamos un JaxBContext
            JAXBContext contexto = JAXBContext.newInstance(Concesionario.class);
            // Creamos el objeto Marshaller usando el JaxB contexto
            Marshaller marshaller = contexto.createMarshaller();
            // Establecemos las propiedades para el JSON
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            // Establecemos para el objeto marshaller el formato de salida
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Creamos el fichero y grababamos el objeto JSON como fichero
            marshaller.marshal(concesionario, System.out);
            marshaller.marshal(concesionario, file);

        } catch (JAXBException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

}
