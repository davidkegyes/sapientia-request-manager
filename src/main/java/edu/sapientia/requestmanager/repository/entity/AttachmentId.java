package edu.sapientia.requestmanager.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentId implements Serializable {

    private Long id;

    private String uuid;

}
