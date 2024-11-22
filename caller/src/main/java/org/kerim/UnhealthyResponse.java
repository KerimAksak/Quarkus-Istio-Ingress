package org.kerim;

import java.util.List;

public class UnhealthyResponse {
    public String status;
    public List<String> checks;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }
}
