package app.dll.test.data.useCase;

import app.dll.test.data.repository.LocalTehillimRepository;
import jakarta.inject.Inject;

public class ClearDbUseCase {
    private final LocalTehillimRepository localRepository;
    @Inject
    public ClearDbUseCase(LocalTehillimRepository localRepository) {
        this.localRepository = localRepository;
    }

    public void clearDb() {
        localRepository.deleteALL();
    }


}
