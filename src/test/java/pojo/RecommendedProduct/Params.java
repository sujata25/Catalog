
package pojo.RecommendedProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "dims",
    "expanded",
    "q"
})
public class Params {

    @JsonProperty("dims")
    private List<String> dims = new ArrayList<String>();
    @JsonProperty("expanded")
    private List<String> expanded = new ArrayList<String>();
    @JsonProperty("q")
    private List<String> q = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dims")
    public List<String> getDims() {
        return dims;
    }

    @JsonProperty("dims")
    public void setDims(List<String> dims) {
        this.dims = dims;
    }

    @JsonProperty("expanded")
    public List<String> getExpanded() {
        return expanded;
    }

    @JsonProperty("expanded")
    public void setExpanded(List<String> expanded) {
        this.expanded = expanded;
    }

    @JsonProperty("q")
    public List<String> getQ() {
        return q;
    }

    @JsonProperty("q")
    public void setQ(List<String> q) {
        this.q = q;
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
        return new HashCodeBuilder().append(dims).append(expanded).append(q).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Params) == false) {
            return false;
        }
        Params rhs = ((Params) other);
        return new EqualsBuilder().append(dims, rhs.dims).append(expanded, rhs.expanded).append(q, rhs.q).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
