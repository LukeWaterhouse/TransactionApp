package csc1035.project3;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;

    @Entity
    @Table(name = "MODULE")
    public class Module {

        @Id
        @Column
        private String moduleCode;

        @Column
        private String moduleName;


        @ManyToMany(mappedBy = "modules")
        private Set<Student> employees = new HashSet<>();

        public Module(String moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Module(String moduleCode, String moduleName) {
            this.moduleCode = moduleCode;
            this.moduleName = moduleName;
        }

        public Module() {
        }

        public String getModuleCode() {
            return moduleCode;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }
    }

