
package rest.todo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para priority.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="priority">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HIGH"/>
 *     &lt;enumeration value="MID"/>
 *     &lt;enumeration value="LOW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "priority")
@XmlEnum
public enum Priority {

    HIGH,
    MID,
    LOW;

    public String value() {
        return name();
    }

    public static Priority fromValue(String v) {
        return valueOf(v);
    }

}
