package util;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * Utilitário para métodos baseados no underscorejs. ver {@link
 * <a>http://underscorejs.org</a>}
 * 
 * @author Marcos V. Candeia
 */
public final class _ {

	/**
	 * Retorna true caso exista algum elemento na {@code colecao} que atenda ao
	 * {@code predicado}
	 * 
	 * @param Colecao
	 *            a coleção de objetos
	 * @param predicado
	 *            um predicado que retorna true ou false dependendo do
	 *            {@code elemento}
	 * @return true caso o {@code predicado} valide algum {@code elemento} da
	 *         {@code colecao}.
	 */
	public static <T> boolean
			some(Collection<T> colecao, Predicate<T> predicado) {
		for (T elemento : colecao) {
			if (predicado.apply(elemento)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna uma coleção de objetos que sejam validados pelo {@code predicado}
	 * . Para cada elemento da coleção é aplicada a função de predicado e caso
	 * seja verdadeiro é adicionado na coleção.
	 * 
	 * @param colecao
	 *            a coleção de objetos
	 * @param predicado
	 *            um predicado que retorna true ou false dependendo do
	 *            {@code elemento}
	 * @return A coleção de objetos que o {@code predicado} valide o
	 *         {@code elemento} da {@code colecao}.
	 */
	public static <T> Collection<T> filter(Collection<T> colecao,
			Predicate<T> predicado) {
		Collection<T> ncolecao = new ArrayList<T>();
		for (T elemento : colecao) {
			if (predicado.apply(elemento)) {
				ncolecao.add(elemento);
			}
		}
		return ncolecao;
	}

	/**
	 * Retorna uma nova coleção de objetos aplicados a uma {@code funcao},
	 * exemplo, quando se quer mapear uma lista de Strings em uma lista de
	 * inteiros a função seria de String para Inteiro e é retornada uma coleção
	 * de Inteiros
	 * 
	 * @param Colecao
	 *            a coleção de objetos
	 * @param predicado
	 *            um predicado que retorna true ou false dependendo do
	 *            {@code elemento}
	 * @return Uma nova coleção de objetos aplicadas na {@code funcao}
	 */
	public static <T, F> Collection<F> map(Collection<T> colecao,
			Function<T, F> funcao) {
		Collection<F> ncolecao = new ArrayList<F>();
		for (T elemento : colecao) {
			ncolecao.add(funcao.apply(elemento));
		}
		return ncolecao;
	}

	/**
	 * Retorna o elemento onde o {@code predicado} retorna true
	 * 
	 * @param colecao
	 *            a coleção de objetos
	 * @param funcao
	 *            a função que vai ser aplicada de um valor T para um valor F de
	 *            retorno
	 * @return Um objeto onde o {@code predicado} retorna true
	 */
	public static <T> T find(Collection<T> colecao, Predicate<T> predicado) {
		for (T elemento : colecao) {
			if (predicado.apply(elemento)) {
				return elemento;
			}
		}
		return null;
	}

	/**
	 * @param Colecao
	 *            a coleção de objetos
	 * @param funcao
	 *            a função que vai ser aplicada de um valor T para um valor F de
	 *            retorno
	 * @param funcaoConcat
	 *            a função que une o resultado da operação da {@code funcao}
	 *            sobre um {@code elemento} da {@code colecao}
	 * @param base
	 *            um objeto base para a operação
	 * @return O valor que foi aplicado a {@funcaoConcat} que á
	 *         função de concatenação de retornos da {@funcao} aplicada
	 *         a todos os elementos.
	 */
	public static <T, F> F reduce(Collection<T> colecao, Function<T, F> funcao,
			Function<F, Function<F, F>> funcaoConcat, F base) {
		for (T elemento : colecao) {
			base = funcaoConcat.apply(base).apply(funcao.apply(elemento));
		}
		return base;
	}
}
