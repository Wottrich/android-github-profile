package wottrich.github.io.githubprofile.ui.profile

import github.io.wottrich.datasource.GithubDataSourceInterface
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.resource.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import wottrich.github.io.githubprofile.BaseUnitTest
import wottrich.github.io.githubprofile.R

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 */

class ProfileViewModelTest : BaseUnitTest() {

    private lateinit var service: GithubDataSourceInterface
    private lateinit var sut: ProfileViewModel

    private val dummyProfile = Profile(
        "Wottrich",
        "Lucas Cruz Wottrich",
        "Android/iOS",
        "",
        10,
        10
    )

    private val dummyRepository = Repository(
        "NetunoNavigationPod",
        "Wottrich/NetunoNavigationPod",
        "",
        "\uD83D\uDD31 NetunoNavigationPod \uD83D\uDD31 - To take navigation to the next level (MIT License)",
        "",
        "Kotlin",
        true,
        10,
        watchers = 1,
        openPullRequestCount = 1,
        owner = dummyProfile
    )

    @Before
    fun setUp() {
        service = mockk()
        sut = ProfileViewModel(
            dispatchers = coroutinesTestRule.dispatchers,
            service
        )
    }

    @Test
    fun `WHEN requests profile with valid login THEN must call profile and repository service`() {
        val expectedProfile = dummyProfile
        val login = expectedProfile.login
        val expectedRepositoriesList = listOf(dummyRepository, dummyRepository)
        mockLoadProfile(Resource.success(expectedProfile))
        mockLoadRepository(Resource.success(expectedRepositoriesList))

        sut.loadServices(login)

        coVerify(exactly = 1) { service.loadProfile(login) }
        coVerify(exactly = 1) { service.loadRepositories(login) }

        assertTrue(sut.profileState.value.headerState.isSuccess())
        assertEquals(
            expectedProfile,
            sut.profileState.value.headerState.success
        )

        assertTrue(sut.profileState.value.repositoriesState.isSuccess())
        assertEquals(
            expectedRepositoriesList,
            sut.profileState.value.repositoriesState.success
        )
    }

    @Test
    fun `WHEN requests profile twice with same login THEN livedata must notify error`() {
        sut.error.observeForever { }
        val expectedProfile = dummyProfile
        val login = expectedProfile.login
        val expectedRepositoriesList = listOf(dummyRepository, dummyRepository)
        mockLoadProfile(Resource.success(expectedProfile))
        mockLoadRepository(Resource.success(expectedRepositoriesList))

        sut.loadServices(login)

        coVerify(exactly = 1) { service.loadProfile(login) }
        coVerify(exactly = 1) { service.loadRepositories(login) }

        sut.loadServices(login)

        assertEquals(
            sut.error.value,
            R.string.equal_login_error
        )
    }

    private fun mockLoadProfile(resourceProfile: Resource<Profile>) {
        coEvery { service.loadProfile(any()) } returns flow { emit(resourceProfile) }
    }

    private fun mockLoadRepository(resourceProfile: Resource<List<Repository>>) {
        coEvery { service.loadRepositories(any()) } returns flow { emit(resourceProfile) }
    }

}