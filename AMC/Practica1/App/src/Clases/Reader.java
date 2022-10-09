/** 
 *
 * @author ElPsy
 * */

package Clases;
import java.io.*;
import java.util.ArrayList;

import Clases.Punto;

public class Reader {
	BufferedReader reader;
	ArrayList<Punto> puntos;
	public Reader(String file) throws FileNotFoundException {
		this.reader = new BufferedReader(
			new FileReader(file)
		);
		
		this.puntos = new ArrayList<Punto>();
		
		try {
			this.readFile();
			this.closeFile();
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e);
		}
	}

	public ArrayList<Punto> getPuntos() {
		return this.puntos;
	}

	void readFile() throws IOException {
		String line = this.reader.readLine();
		while (!line.contains("NODE_COORD_SECTION")) {
			line = reader.readLine();
		}

		while ((line = this.reader.readLine()) != null) {
			String[] data = line.split(" ");	
			this.puntos.add(
				new Punto(Double.parseDouble(data[1]), Double.parseDouble(data[2]))
			);
		}
	}
	
	void closeFile() throws IOException{
		this.reader.close();
	}
}
