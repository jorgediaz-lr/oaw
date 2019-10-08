/*******************************************************************************
* Copyright (C) 2012 INTECO, Instituto Nacional de Tecnologías de la Comunicación, 
* This program is licensed and may be used, modified and redistributed under the terms
* of the European Public License (EUPL), either version 1.2 or (at your option) any later 
* version as soon as they are approved by the European Commission.
* Unless required by applicable law or agreed to in writing, software distributed under the 
* License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
* ANY KIND, either express or implied. See the License for the specific language governing 
* permissions and more details.
* You should have received a copy of the EUPL1.2 license along with this program; if not, 
* you may find it at http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX:32017D0863
* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
* Modificaciones: MINHAFP (Ministerio de Hacienda y Función Pública) 
* Email: observ.accesibilidad@correo.gob.es
******************************************************************************/
package es.inteco.rastreador2.actionform.semillas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * The Class SemillaForm.
 */
public class SemillaForm extends ValidatorForm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The nombre. */
	private String nombre;

	/** The nombre antiguo. */
	private String nombre_antiguo;

	/** The lista urls. */
	private List<String> listaUrls;

	/** The lista urls string. */
	private String listaUrlsString;

	/** The acronimo. */
	// private String dependencia;
	private String acronimo;

	/** The asociada. */
	private boolean asociada;

	/** The activa. */
	private boolean activa;

	/** The activa str. */
	private String activaStr;

	/** The rastreo asociado. */
	private long rastreoAsociado;

	/** The categoria. */
	private CategoriaForm categoria;

	/** The in directory. */
	private boolean inDirectory;

	/** The in directory str. */
	private String inDirectoryStr;

	/** The dependencias. */
	private List<DependenciaForm> dependencias;
	
	private AmbitoForm ambito;
	
	private ComplejidadForm complejidad;
	
	private List<EtiquetaForm> etiquetas;

	/**
	 * Instantiates a new semilla form.
	 */
	public SemillaForm() {
		this.activa = true;
	}

	/**
	 * Checks if is asociada.
	 *
	 * @return true, if is asociada
	 */
	public boolean isAsociada() {
		return asociada;
	}

	/**
	 * Sets the asociada.
	 *
	 * @param asociada the new asociada
	 */
	public void setAsociada(boolean asociada) {
		this.asociada = asociada;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the lista urls.
	 *
	 * @return the lista urls
	 */
	public List<String> getListaUrls() {
		return listaUrls;
	}

	/**
	 * Sets the lista urls.
	 *
	 * @param listaUrls the new lista urls
	 */
	public void setListaUrls(List<String> listaUrls) {
		this.listaUrls = listaUrls;
	}

	/**
	 * Adds the list url.
	 *
	 * @param url the url
	 */
	public void addListUrl(String url) {
		if (this.listaUrls == null) {
			this.listaUrls = new ArrayList<>();
		}

		this.setListaUrlsString(url.replace("\n", ";"));

		List<String> tmp = Arrays.asList(this.listaUrlsString.split(";"));
		for (int i = 0; i < tmp.size(); i++) {
			this.listaUrls.add(tmp.get(i).trim());
		}

	}

	/**
	 * Gets the lista urls string.
	 *
	 * @return the lista urls string
	 */
	public String getListaUrlsString() {
		return listaUrlsString;
	}

	/**
	 * Sets the lista urls string.
	 *
	 * @param listaUrlsString the new lista urls string
	 */
	public void setListaUrlsString(String listaUrlsString) {
		this.listaUrlsString = listaUrlsString;
	}

	/**
	 * Gets the nombre antiguo.
	 *
	 * @return the nombre antiguo
	 */
	public String getNombre_antiguo() {
		return nombre_antiguo;
	}

	/**
	 * Sets the nombre antiguo.
	 *
	 * @param nombre_antiguo the new nombre antiguo
	 */
	public void setNombre_antiguo(String nombre_antiguo) {
		this.nombre_antiguo = nombre_antiguo;
	}

	/**
	 * Gets the rastreo asociado.
	 *
	 * @return the rastreo asociado
	 */
	public long getRastreoAsociado() {
		return rastreoAsociado;
	}

	/**
	 * Sets the rastreo asociado.
	 *
	 * @param rastreoAsociado the new rastreo asociado
	 */
	public void setRastreoAsociado(long rastreoAsociado) {
		this.rastreoAsociado = rastreoAsociado;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public CategoriaForm getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 */
	public void setCategoria(CategoriaForm categoria) {
		this.categoria = categoria;
	}

	/**
	 * Reset.
	 *
	 * @param mapping the mapping
	 * @param request the request
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		if (categoria == null) {
			categoria = new CategoriaForm();
		}
	}

	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SemillaForm that = (SemillaForm) o;

		return id == that.id;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	// public String getDependencia() {
	// return dependencia;
	// }
	//
	// public void setDependencia(String dependencia) {
	// this.dependencia = dependencia;
	// }

	/**
	 * Gets the acronimo.
	 *
	 * @return the acronimo
	 */
	public String getAcronimo() {
		return acronimo;
	}

	/**
	 * Sets the acronimo.
	 *
	 * @param acronimo the new acronimo
	 */
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	/**
	 * Checks if is activa.
	 *
	 * @return true, if is activa
	 */
	public boolean isActiva() {
		return activa;
	}

	/**
	 * Sets the activa.
	 *
	 * @param activa the new activa
	 */
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	/**
	 * Gets the activa str.
	 *
	 * @return the activa str
	 */
	public String getActivaStr() {
		return activaStr;
	}

	/**
	 * Sets the activa str.
	 *
	 * @param activaStr the new activa str
	 */
	public void setActivaStr(String activaStr) {
		if (StringUtils.isNotEmpty(activaStr) && activaStr.equalsIgnoreCase(Boolean.TRUE.toString())) {
			this.setActiva(true);
		} else {
			this.setActiva(false);
		}

		this.activaStr = activaStr;
	}

	/**
	 * Checks if is in directory.
	 *
	 * @return true, if is in directory
	 */
	public boolean isInDirectory() {
		return inDirectory;
	}

	/**
	 * Sets the in directory.
	 *
	 * @param inDirectory the new in directory
	 */
	public void setInDirectory(boolean inDirectory) {
		this.inDirectory = inDirectory;
	}

	/**
	 * Gets the in directory str.
	 *
	 * @return the in directory str
	 */
	public String getInDirectoryStr() {
		return inDirectoryStr;
	}

	/**
	 * Sets the in directory str.
	 *
	 * @param inDirectoryStr the new in directory str
	 */
	public void setInDirectoryStr(String inDirectoryStr) {
		if (StringUtils.isNotEmpty(inDirectoryStr) && inDirectoryStr.equalsIgnoreCase(Boolean.TRUE.toString())) {
			this.setInDirectory(true);
		} else {
			this.setInDirectory(false);
		}

		this.inDirectoryStr = inDirectoryStr;
	}

	/**
	 * Gets the dependencias.
	 *
	 * @return the dependencias
	 */
	public List<DependenciaForm> getDependencias() {
		return dependencias;
	}

	/**
	 * Sets the dependencias.
	 *
	 * @param dependencias the new dependencias
	 */
	public void setDependencias(List<DependenciaForm> dependencias) {
		this.dependencias = dependencias;
	}

	/**
	 * Adds the dependencia.
	 *
	 * @param dependencia the dependencia
	 */
	public void addDependencia(DependenciaForm dependencia) {
		if (this.dependencias == null) {
			this.dependencias = new ArrayList<DependenciaForm>();
		}
		this.dependencias.add(dependencia);
	}

	/**
	 * Adds the dependencia por nombre.
	 *
	 * @param nombre the nombre
	 */
	public void addDependenciaPorNombre(String nombre) {
		if (this.dependencias == null) {
			this.dependencias = new ArrayList<DependenciaForm>();
		}
		// DependenciaForm dependencia = new DependenciaForm();
		// dependencia.setName(nombre);
		// this.dependencias.add(dependencia);

		if (!StringUtils.isEmpty(nombre)) {
			String[] nombres = nombre.split("\r\n");

			// Try split without \r
			if (nombres.length == 1) {
				nombres = nombre.split("\n");
			}

			for (String currentNombre : nombres) {
				DependenciaForm dependencia = new DependenciaForm();
				dependencia.setName(currentNombre.trim());
				this.dependencias.add(dependencia);
			}
		}

	}

	/**
	 * Sets the category name.
	 *
	 * @param categoryName the new category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoria = new CategoriaForm();
		categoria.setName(categoryName);
	}

	/**
	 * Sets the id str.
	 *
	 * @param strId the new id str
	 */
	public void setIdStr(String strId) {
		if (strId != null && !StringUtils.isEmpty(strId)) {
			this.setId(Long.parseLong(strId));
		} else {
			this.setId(null);
		}

	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SemillaForm [id=" + id + ", nombre=" + nombre + ", nombre_antiguo=" + nombre_antiguo + ", listaUrls="
				+ listaUrls + ", listaUrlsString=" + listaUrlsString
				+ /* ", dependencia=" + dependencia + */ ", acronimo=" + acronimo + ", asociada=" + asociada
				+ ", activa=" + activa + ", activaStr=" + activaStr + ", rastreoAsociado=" + rastreoAsociado
				+ ", categoria=" + categoria + ", inDirectory=" + inDirectory + ", inDirectoryStr=" + inDirectoryStr
				+ ", dependencias=" + dependencias + "]";
	}

}