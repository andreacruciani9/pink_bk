package it.pink.pink.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AccessTokenRes {
    private String accessToken;
    private UUID id;
}
