
package pojo.CuBundles;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "multiselect",
    "refinements",
    "count"
})
public class Refinement {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("multiselect")
    private Boolean multiselect;
    @JsonProperty("refinements")
    private Object refinements;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("multiselect")
    public Boolean getMultiselect() {
        return multiselect;
    }

    @JsonProperty("multiselect")
    public void setMultiselect(Boolean multiselect) {
        this.multiselect = multiselect;
    }

    @JsonProperty("refinements")
    public Object getRefinements() {
        return refinements;
    }

    @JsonProperty("refinements")
    public void setRefinements(Object refinements) {
        this.refinements = refinements;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(multiselect).append(refinements).append(count).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Refinement) == false) {
            return false;
        }
        Refinement rhs = ((Refinement) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(multiselect, rhs.multiselect).append(refinements, rhs.refinements).append(count, rhs.count).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
