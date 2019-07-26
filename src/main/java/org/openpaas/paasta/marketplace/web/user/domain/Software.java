package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;

@Data
public class Software extends AbstractEntity {

    private Long id;

    private Category category;

    private String categoryName;

//    @ManyToOne
//    private Pkg pkg;
//
//    @OneToMany
//    @JsonProperty(access = Access.WRITE_ONLY)
//    private List<Screenshot> screenshotList;

//    @Transient
//    @Replica
//    private List<Screenshot> screenshotListReplica;

//    @OneToMany(mappedBy = "software")
//    // @JsonIgnore
//    @JsonProperty(access = Access.WRITE_ONLY)
//    private List<SoftwareInstance> softwareInstanceList;
//
//    @Transient
//    @Replica
//    private List<SoftwareInstance> softwareInstanceListReplica;

//    @Column(unique = true)
    @NotNull
    @Size(min=8, max = 100)
    private String name;

//    @NotNull
//    @Size(min=10)
    private String description;

//    @NotNull
//    @Size(min=10)
    private String detailDescription;

    private File appIcon;

    // 아이콘 파일 이름
    private String iconFileName;

    // 아이콘 파일 경로
    private String iconFilePath;

    // 사용자 설명서 매뉴얼 파일 이름
    private String manualFileName;

    // 사용자 설명서 매뉴얼 파일 경로
    private String manualFilePath;

    // SW파일 파일 이름
    private String swFileName;

    // SW파일 파일 경로
    private String swFilePath;

//    @NotNull
    private Type type;

//    @NotNull
    private Yn yn;

    private MultipartFile IconFile;

    private MultipartFile productFile;

    private MultipartFile environmentFile;

    private MultipartFile[] screenshotFiles;

    private String version;

//    @NotNull
    private String price;

}