

import java.net.MalformedURLException;

public class TestaSimilaridade {

  public static void main(String[] args) {
 
    WordNetParser wordNetParser = null;
    
	
    String classe = "USA";
    String candidato = "United States";

    try {
	
      wordNetParser = new WordNetParser( "WordNet", "/3.0/dict" );
     
	  
      for(int i = 1; i <= 1; i++) {
    	  
        double similaridadeLIN = wordNetParser.calcularSimilaridadeLIN( classe, candidato );
        double similaridadeJiangAndConrath = wordNetParser.calcularSimilaridadeJiangAndConrath( classe, candidato );
        double similaridadeAdaptedLesk = wordNetParser.calcularSimilaridadeAdaptedLesk( classe, candidato );
        double similaridadeAdaptedLeskTanimoto = wordNetParser.calcularSimilaridadeAdaptedLeskTanimoto( classe, candidato );
        double similaridadeLeacockAndChodorow = wordNetParser.calcularSimilaridadeLeacockAndChodorow( classe, candidato );
        double similaridadeResnik = wordNetParser.calcularSimilaridadeResnik( classe, candidato );
        double similaridadeWuAndPalmer = wordNetParser.calcularSimilaridadeWuAndPalmer( classe, candidato );
        
      
        
        System.out.println( "  similaridadeLIN: " + similaridadeLIN );
        System.out.println( "  similaridadeJiangAndConrath: " + similaridadeJiangAndConrath + "\n\n" );
        System.out.println( "  similaridadeAdaptedLesk: " + similaridadeAdaptedLesk );
        System.out.println( "  similaridadeAdaptedLeskTanimoto: " + similaridadeAdaptedLeskTanimoto + "\n\n" );
        System.out.println( "  similaridadeLeacockAndChodorow: " + similaridadeLeacockAndChodorow + "\n\n" );
        System.out.println( "  similaridadeResnik: " + similaridadeResnik + "\n\n" );
        System.out.println( "  similaridadeWuAndPalmer: " + similaridadeWuAndPalmer + "\n\n" );
        
    	System.out.println( "\n\n" );
    	
      }
      
    }
	
    catch (MalformedURLException e1) {
	
      System.out.println( "ERRO AO CARREGAR O PARSER DO WORDNET" );

      e1.printStackTrace();
		
      return;
		
    }  
  
  }
  
}
