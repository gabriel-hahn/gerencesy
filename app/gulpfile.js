var gulp = require('gulp');
var sass = require('gulp-sass');
var header = require('gulp-header');
var cleanCSS = require('gulp-clean-css');
var rename = require("gulp-rename");
var uglify = require('gulp-uglify');
var pug = require('gulp-pug');
var beautify = require('gulp-html-beautify');
var pkg = require('./package.json');
var browserSync = require('browser-sync').create();
// Set the banner content
var banner = ['/*!\n',
  ' * Start Bootstrap - <%= pkg.title %> v<%= pkg.version %> (<%= pkg.homepage %>)\n',
  ' * Copyright 2013-' + (new Date()).getFullYear(), ' <%= pkg.author %>\n',
  ' * Licensed under <%= pkg.license %> (https://github.com/BlackrockDigital/<%= pkg.name %>/blob/master/LICENSE)\n',
  ' */\n',
  ''
].join('');
// Copy third party libraries from /node_modules into /vendor
gulp.task('vendor', function () {
  // Bootstrap
  gulp.src([
    './node_modules/bootstrap/dist/**/*',
    '!./node_modules/bootstrap/dist/css/bootstrap-grid*',
    '!./node_modules/bootstrap/dist/css/bootstrap-reboot*'
  ])
  // UI.Bootstrap
  gulp.src([
    './node_modules/angular-ui-bootstrap/dist/ui-bootstrap-csp.css',
    './node_modules/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js',
    './node_modules/angular-ui-bootstrap/dist/ui-bootstrap.js'
  ])
    .pipe(gulp.dest('./vendor/angular-ui-bootstrap/'))
  // DataTables
  gulp.src([
    './node_modules/datatables.net/js/*.js',
    './node_modules/datatables.net-bs4/js/*.js',
    './node_modules/datatables.net-bs4/css/*.css'
  ])
    .pipe(gulp.dest('./vendor/datatables/'))
  // AngularJS
  gulp.src([
    './node_modules/angular/angular.js',
    './node_modules/angular/index.js',
    './node_modules/angular/*.css'
  ])
    .pipe(gulp.dest('./vendor/angular/'))
  // Angular UI Notification
  gulp.src([
    './node_modules/angular-ui-notification/dist/angular-ui-notification.min.css',
    './node_modules/angular-ui-notification/dist/angular-ui-notification.min.js'
  ])
    .pipe(gulp.dest('./vendor/angular-ui-notification/'))
  // Angular Drag and Drop
  gulp.src([
    './node_modules/angular-drag-and-drop-lists/angular-drag-and-drop-lists.js'
  ])
    .pipe(gulp.dest('./vendor/angular-drag-and-drop-lists/'))
  // Angular-route
  gulp.src([
    './node_modules/angular-route/angular-route.js',
    './node_modules/angular-route/index.js',
    './node_modules/angular-route/*.css'
  ])
    .pipe(gulp.dest('./vendor/angular-route/'))
  // Font Awesome
  gulp.src([
    './node_modules/font-awesome/**/*',
    '!./node_modules/font-awesome/{less,less/*}',
    '!./node_modules/font-awesome/{scss,scss/*}',
    '!./node_modules/font-awesome/.*',
    '!./node_modules/font-awesome/*.{txt,json,md}'
  ])
    .pipe(gulp.dest('./vendor/font-awesome'))
  // jQuery
  gulp.src([
    './node_modules/jquery/dist/*',
    '!./node_modules/jquery/dist/core.js'
  ])
    .pipe(gulp.dest('./vendor/jquery'))
  // jQuery Easing
  gulp.src([
    './node_modules/jquery.easing/*.js'
  ])
    .pipe(gulp.dest('./vendor/jquery-easing'))
});
// Compile SCSS
gulp.task('css:compile', function () {
  return gulp.src('./src/scss/**/*.scss')
    .pipe(sass.sync({
      outputStyle: 'expanded'
    }).on('error', sass.logError))
    .pipe(gulp.dest('./src/css'))
});
// Minify CSS
gulp.task('css:minify', ['css:compile'], function () {
  return gulp.src([
    './src/css/*.css',
    '!./src/css/*.min.css'
  ])
    .pipe(cleanCSS())
    .pipe(rename({
      suffix: '.min'
    }))
    .pipe(gulp.dest('./src/css'))
    .pipe(browserSync.stream());
});
// CSS
gulp.task('css', ['css:compile', 'css:minify']);
// Minify JavaScript
gulp.task('js:minify', function () {
  return gulp.src([
    './src/js/*.js',
    '!./src/js/*.min.js'
  ])
    .pipe(uglify())
    .pipe(rename({
      suffix: '.min'
    }))
    .pipe(gulp.dest('./src/js'))
    .pipe(browserSync.stream());
});
// JS
gulp.task('js', ['js:minify']);
// Default task
gulp.task('default', ['css', 'js', 'vendor']);
// Configure the browserSync task
gulp.task('browserSync', function () {
  browserSync.init({
    server: {
      baseDir: "./"
    }
  });
});
// Dev task
gulp.task('dev', ['css', 'js', 'browserSync'], function () {
  gulp.watch('./src/scss/**/*.scss', ['css']);
  gulp.watch('./src/js/*.js', ['js']);
  gulp.watch('./src/**/*.html', browserSync.reload);
});
