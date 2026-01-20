package org.Icet.springtask.config;

import jakarta.transaction.Transactional;
import org.Icet.springtask.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VersionFixRunner implements CommandLineRunner {

    private final SeatRepository seatRepository;

    public VersionFixRunner(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        seatRepository.findAll().stream()
                .filter(s -> s.getVersion() == 0L && s.getId() != null)
                .forEach(s -> {
                    s.setVersion(s.getVersion());
                    seatRepository.save(s);
                });
    }
}