package wottrich.github.io.githubprofile

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 24/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
/*
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <data>
        <import type="android.view.View" />

        <import type="wottrich.github.io.githubprofile.viewModel.ProfileViewModel" />

        <variable
            name="viewModel"
            type="ProfileViewModel" />

    </data>

    <androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:context=".view.ProfileActivity">

        <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/app_bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/transparent"
            android:elevation="0dp"
            android:fitsSystemWindows="true"
            android:theme="@style/AppTheme.AppBarOverlay"
            app:elevation="0dp">

            <com.google.android.material.appbar.CollapsingToolbarLayout
                android:id="@+id/toolbar_layout"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:elevation="0dp"
                android:fitsSystemWindows="true"
                app:contentScrim="?attr/colorPrimary"
                app:elevation="0dp"
                app:layout_scrollFlags="noScroll"
                app:toolbarId="@+id/toolbar">

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical">

                    <TextView
                        android:id="@+id/tvWelcomeFindProfile"
                        style="@style/RobotoBold.Title"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="10dp"
                        android:text="@string/welcome_find_profile"
                        android:textAlignment="center"
                        android:visibility="@{ viewModel.profileLogin == null ? View.VISIBLE : View.GONE  }"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintStart_toStartOf="parent"
                        app:layout_constraintTop_toTopOf="parent" />

                    <TextView
                        android:id="@+id/tvProfileError"
                        style="@style/RobotoBold.SubTitle"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="10dp"
                        android:text="@{ viewModel.profileError }"
                        android:textAlignment="center"
                        android:visibility="@{ viewModel.profileErrorVisibility }"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintStart_toStartOf="parent"
                        app:layout_constraintTop_toTopOf="parent"
                        tools:text="Error to search profile" />

                    <TextView
                        android:id="@+id/tvRepositoriesError"
                        style="@style/RobotoBold.SubTitle"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@{ viewModel.repositoriesError }"
                        android:textAlignment="center"
                        android:visibility="@{ viewModel.repositoriesErrorVisibility }"
                        tools:text="Error to find repositories" />

                    <ProgressBar
                        android:id="@+id/progressBarProfile"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:visibility="@{ viewModel.loadingProfile != null ? viewModel.loadingProfile : View.GONE }"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintStart_toStartOf="parent"
                        app:layout_constraintTop_toTopOf="parent" />


                    <androidx.constraintlayout.widget.ConstraintLayout
                        android:id="@+id/clProfile"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:paddingBottom="10dp"
                        android:visibility="gone"
                        tools:visibility="visible">

                        <ImageView
                            android:id="@+id/imgProfile"
                            android:layout_width="86dp"
                            android:layout_height="86dp"
                            android:layout_marginStart="10dp"
                            android:layout_marginTop="10dp"
                            android:layout_marginBottom="10dp"
                            android:contentDescription="@string/image_profile_content_description"
                            android:src="@drawable/ic_person_32"
                            app:imageFromUrl="@{ viewModel.profile.avatarUrl }"
                            app:layout_constraintStart_toStartOf="parent"
                            app:layout_constraintTop_toTopOf="parent" />

                        <TextView
                            android:id="@+id/tvName"
                            style="@style/RobotoBold.Title"
                            android:layout_width="0dp"
                            android:layout_height="wrap_content"
                            android:layout_marginStart="5dp"
                            android:layout_marginTop="10dp"
                            android:layout_marginEnd="5dp"
                            android:text="@{ viewModel.profile.name }"
                            app:layout_constraintEnd_toEndOf="parent"
                            app:layout_constraintStart_toEndOf="@id/imgProfile"
                            app:layout_constraintTop_toTopOf="parent"
                            tools:text="Lucas Cruz Wottrich" />

                        <TextView
                            android:id="@+id/tvBio"
                            style="@style/RobotoBold.SubTitle"
                            android:layout_width="0dp"
                            android:layout_height="wrap_content"
                            android:layout_marginStart="5dp"
                            android:layout_marginEnd="5dp"
                            android:text="@{ viewModel.profile.bio }"
                            app:layout_constraintEnd_toEndOf="parent"
                            app:layout_constraintStart_toEndOf="@id/imgProfile"
                            app:layout_constraintTop_toBottomOf="@id/tvName"
                            tools:text="Mobile Developer" />

                        <TextView
                            android:id="@+id/tvLogin"
                            style="@style/RobotoRegular.Description"
                            android:layout_width="0dp"
                            android:layout_height="wrap_content"
                            android:layout_marginStart="5dp"
                            android:layout_marginEnd="5dp"
                            android:text="@{ viewModel.profile.login }"
                            app:layout_constraintEnd_toEndOf="parent"
                            app:layout_constraintStart_toEndOf="@id/imgProfile"
                            app:layout_constraintTop_toBottomOf="@id/tvBio"
                            tools:text="Wottrich" />

                        <LinearLayout
                            android:layout_width="0dp"
                            android:layout_height="wrap_content"
                            android:layout_marginStart="5dp"
                            android:layout_marginEnd="5dp"
                            android:orientation="vertical"
                            app:layout_constraintEnd_toEndOf="parent"
                            app:layout_constraintStart_toEndOf="@id/imgProfile"
                            app:layout_constraintTop_toBottomOf="@id/tvLogin">

                            <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginBottom="5dp"
                                android:orientation="horizontal">

                                <TextView
                                    style="@style/RobotoBold.SubTitle"
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:text="@string/profile_followers"
                                    android:textAlignment="center" />


                                <TextView
                                    android:id="@+id/tvFollowers"
                                    style="@style/RobotoRegular.SubTitle"
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:layout_marginStart="10dp"
                                    android:text="@{ String.valueOf(viewModel.profile.followers) }"
                                    tools:text="52" />


                            </LinearLayout>

                            <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:orientation="horizontal">

                                <TextView
                                    style="@style/RobotoBold.SubTitle"
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:text="@string/profile_following"
                                    android:textAlignment="center" />


                                <TextView
                                    android:id="@+id/tvFollowing"
                                    style="@style/RobotoRegular.SubTitle"
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:layout_marginStart="10dp"
                                    android:text="@{ String.valueOf(viewModel.profile.following) }"
                                    tools:text="45" />

                            </LinearLayout>


                        </LinearLayout>

                    </androidx.constraintlayout.widget.ConstraintLayout>
                </LinearLayout>

            </com.google.android.material.appbar.CollapsingToolbarLayout>
        </com.google.android.material.appbar.AppBarLayout>

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rvRepositories"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"
            app:reverseLayout="false" />

        <ProgressBar
            android:id="@+id/progressBarRepositories"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:visibility="@{ viewModel.loadingRepositories != null ? viewModel.loadingProfile : View.GONE }" />

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

</layout>

 */