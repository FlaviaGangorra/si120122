package modelo;

/**
 * Palavras-chave de Java
 * 
 * @author
 * 
 */
public class KeyWords {

	String palavrasChave = "one of,  "
			+ "abstract,    continue,    for,           new,           switch,"
			+ "assert,      default,     if ,           package,       synchronized,"
			+ "boolean,     do,          goto,          private,       this,"
			+ "break,       double,      implements,    protected,     throw,"
			+ "byte,        else,        import,        public,        throws,"
			+ "case,        enum,        instanceof,    return,        transient,"
			+ "catch,       extends,     int,           short,         try,"
			+ "char,        final,       interface,     static,        void,"
			+ "class,       finally,     long,          strictfp,      volatile,"
			+ "const,       float,       native ,       super,         while      ";

	/**
	 * Retorna um string contendo todos as palavras-chave de Java
	 * 
	 * @return
	 */
	public String getKeyWords() {
		return palavrasChave;
	}

	/**
	 * Verifica se uma dada palavra passada como parametro eh uma palavra-chave
	 * 
	 * @param palavra
	 *            - a ser verificada
	 * @return true ou false
	 */
	public boolean isKeyWord(String palavra) {
		boolean retorno = false;
		String[] words = palavrasChave.split(",");
		for (int i = 0; i < words.length; i++) {
			if (palavra.equals(words[i].trim())) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

}
