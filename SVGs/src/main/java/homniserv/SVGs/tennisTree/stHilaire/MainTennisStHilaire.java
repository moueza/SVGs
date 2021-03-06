package homniserv.SVGs.tennisTree.stHilaire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class MainTennisStHilaire {
	private static URI getBaseURI() {
		// return
		// UriBuilder.fromUri("http://localhost:8080/com.vogella.jersey.first").build();
		return UriBuilder.fromUri("https://mon-espace-tennis.fft.fr/tournoi/82173737/1027765/decoupages").build();
	}

	// private static final String FILENAME = "E:\\test\\filename.txt";
	private static final String FILENAME = "./filename.txt";

	/**
	 * https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example
	 * 2)/
	 */
	public void saveToFile() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			String content = "This is the content to write into file\n";
			bw.write(content);
			// no need to close it.
			// bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveToFile(String contentt, String filenamee) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filenamee))) {
			// String content = "This is the content to write into file\n";
			bw.write(contentt);
			// no need to close it.
			// bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * http://www.vogella.com/tutorials/REST/article.html REST interne
	 */
	private void clientREST() {
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		String response = target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(Response.class)
				.toString();

		String plainAnswer = target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		// String xmlAnswer =
		// target.path("rest").path("hello").request().accept(MediaType.TEXT_XML).get(String.class);
		String htmlAnswer = target.path("rest").path("hello").request().accept(MediaType.TEXT_HTML).get(String.class);

		// System.out.println("RESPONSE=" + response);
		// System.out.println("plainAnswer=" + plainAnswer);
		System.out.println("plainAnswer=" + plainAnswer);

		/** TODO by Spring bean */
		MainTennisStHilaire main2 = new MainTennisStHilaire();
		main2.saveToFile(plainAnswer, "plainAnswer.txt");

		// System.out.println(xmlAnswer);

		// System.out.println("htmlAnswer=" + htmlAnswer);
		main2.saveToFile(htmlAnswer, "htmlAnswer.txt");

	}

	public void svgCreate() {
		String svgStHilaire = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
				+ "<svg width=\"1000\" height=\"1000\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">" + "\n"
				+ "<line x1=\"0\" y1=\"0\" x2=\"999\" y2=\"999\" style=\"stroke-width: 50px;\"></line>" + "</svg>";
		// + "<line stroke-dasharray=\"10, 5\" x1=\"182\" y1=\"10\" x2=\"350\" y2=\"10\"
		// style=\"stroke-width: 6px;\"></line>"
		svgStHilaire = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
				+ "<svg width=\"1000\" height=\"1000\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
				+ "<line x1=\"0\" y1=\"0\" x2=\"999\" y2=\"999\" style=\"stroke-width: 5px;\" stroke=\"blue\">\n</line>"
				+ "<circle cx=\"50\" cy=\"50\" r=\"40\" style=\"stroke-width: 2px;\" stroke=\"blue\" fill=\"red\" />"
				+ "<ellipse cx=\"240\" cy=\"100\" rx=\"220\" ry=\"30\" style=\"fill:purple\" />"
				+ "<text x=\"50\" y=\"50\" fill=\"black\">I love SVG!</text>" + "\n</svg>";
		/** TODO by Spring bean */
		// MainTennisStHilaire main3 = new MainTennisStHilaire();
		// main3.saveToFile(svgStHilaire, "tennisStHilaire.svg");
		this.saveToFile(svgStHilaire, "tennisStHilaire.svg");
	}

	public void lsDir() {
		System.out.println("txt files are");
		/** https://alvinalexander.com/blog/post/java/create-files-in-directory */

		// create a file that is really a directory
		// File aDirectory = new File("C:/temp");
		File aDirectory = new File("./src/main/java/homniserv/SVGs/tennisTree/stHilaire");

		// get a listing of all files in the directory
		String[] filesInDir = aDirectory.list();

		// sort the list of files (optional)
		// Arrays.sort(filesInDir);

		// have everything i need, just print it now
		for (int i = 0; i < filesInDir.length; i++) {
			String text = filesInDir[i];
			if (text.contains("txt")) {
				System.out.println("file: " + filesInDir[i]);
			}
		}

	}

	public static void main(String[] args) {
		MainTennisStHilaire mainTennisStHilaire = new MainTennisStHilaire();

		mainTennisStHilaire.lsDir();

		mainTennisStHilaire.clientREST();

		// main.saveToFile();
		mainTennisStHilaire.saveToFile();

		mainTennisStHilaire.svgCreate();
	}

}
