package com.neighborhood.application.mappers;

import com.neighborhood.application.Notification.NotificationDto;
import com.neighborhood.domain.Notification.Notification;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

  @Autowired
  private ModelMapper modelMapper;

  public NotificationDto toDto(final Notification entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, NotificationDto.class);
  }

  public Notification toEntity(final NotificationDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Notification.class);
  }
}
