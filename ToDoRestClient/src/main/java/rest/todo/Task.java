
package rest.todo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para task complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="task">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tarea" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contexto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projecto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prioridad" type="{http://ToDo.bigws/}priority" minOccurs="0"/>
 *         &lt;element name="context" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://ToDo.bigws/}priority" minOccurs="0"/>
 *         &lt;element name="project" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="task" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "task", propOrder = {
    "tarea",
    "contexto",
    "projecto",
    "prioridad",
    "context",
    "priority",
    "project",
    "task"
})
public class Task {

    protected int tarea;
    protected String contexto;
    protected String projecto;
    protected Priority prioridad;
    protected String context;
    protected Priority priority;
    protected String project;
    protected int task;

    /**
     * Obtiene el valor de la propiedad tarea.
     * 
     */
    public int getTarea() {
        return tarea;
    }

    /**
     * Define el valor de la propiedad tarea.
     * 
     */
    public void setTarea(int value) {
        this.tarea = value;
    }

    /**
     * Obtiene el valor de la propiedad contexto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContexto() {
        return contexto;
    }

    /**
     * Define el valor de la propiedad contexto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContexto(String value) {
        this.contexto = value;
    }

    /**
     * Obtiene el valor de la propiedad projecto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjecto() {
        return projecto;
    }

    /**
     * Define el valor de la propiedad projecto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjecto(String value) {
        this.projecto = value;
    }

    /**
     * Obtiene el valor de la propiedad prioridad.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPrioridad() {
        return prioridad;
    }

    /**
     * Define el valor de la propiedad prioridad.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPrioridad(Priority value) {
        this.prioridad = value;
    }

    /**
     * Obtiene el valor de la propiedad context.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContext() {
        return context;
    }

    /**
     * Define el valor de la propiedad context.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContext(String value) {
        this.context = value;
    }

    /**
     * Obtiene el valor de la propiedad priority.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Define el valor de la propiedad priority.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPriority(Priority value) {
        this.priority = value;
    }

    /**
     * Obtiene el valor de la propiedad project.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProject() {
        return project;
    }

    /**
     * Define el valor de la propiedad project.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProject(String value) {
        this.project = value;
    }

    /**
     * Obtiene el valor de la propiedad task.
     * 
     */
    public int getTask() {
        return task;
    }

    /**
     * Define el valor de la propiedad task.
     * 
     */
    public void setTask(int value) {
        this.task = value;
    }

}
