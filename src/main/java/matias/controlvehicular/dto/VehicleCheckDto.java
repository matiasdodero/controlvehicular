package matias.controlvehicular.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
public class VehicleCheckDto {
    private Map<String, Integer> checks;

    public VehicleCheckDto() {
    }

    public VehicleCheckDto(Map<String, Integer> checks) {
        this.checks = checks;
    }

    public Map<String, Integer> getChecks() {
        return checks;
    }

    public void setChecks(Map<String, Integer> checks) {
        this.checks = checks;
    }


}
