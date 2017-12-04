package au.com.translatorss.exception;

public class TranslatorssBusinessException extends Exception {

	private static final long serialVersionUID = -582346045513059656L;

	public TranslatorssBusinessException(String mensagem) {
		super(mensagem);
	}

	public TranslatorssBusinessException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public TranslatorssBusinessException(Throwable causa) {
		super(causa);
	}

}
