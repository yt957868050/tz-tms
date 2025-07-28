# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot helicopter training management system (TMS) written in Java 17. The system is designed for managing helicopter training operations including courseware management, training plans, instructor assignments, student enrollment, and certificate management.
- **RuoYi-Vue3**: Vue 3 + Element Plus + Vite (../RuoYi-Vue3 directory)

Both frontends share the same Spring Boot backend architecture.

## Backend Architecture (RuoYi-Vue)

The backend follows a modular Maven multi-module structure:

### Core Modules
- **feian-admin**: Main application entry point and web controllers
- **feian-framework**: Security, configuration, and framework components
- **feian-common**: Shared utilities, exceptions, and base classes
- **feian-system**: System management domain logic and services
- **feian-generator**: Code generation functionality
- **feian-quartz**: Scheduled task management

### Key Technologies
- Spring Boot 3.3.5 with Java 17
- Spring Security + JWT authentication
- MyBatis-plus + PageHelper for data access
- Druid connection pool
- Redis/ for caching and sessions
- MySQL database
- Springdoc (OpenAPI 3) for API documentation

## Frontend Architecture



### RuoYi-Vue3 (Vue 3)
- Vue 3.5.16 + Element Plus 2.9.9
- Vite build system
- Pinia for state management
- Vue Router for navigation
- Axios for HTTP requests

## Common Development Commands

### Backend Development
```bash
# Start backend server (from RuoYi-Vue root)
./ry.sh start

# Stop backend server
./ry.sh stop

# Restart backend server
./ry.sh restart

# Check backend status
./ry.sh status

# Build backend
mvn clean package

# Run backend in development
mvn spring-boot:run -pl feian-admin

# Run tests
mvn test

# Package without tests
mvn clean package -DskipTests
```

### Frontend Development (Vue 3)
```bash
# Navigate to Vue3 directory
cd ../RuoYi-Vue3

# Install dependencies (uses Vite + npm)
npm install

# Start development server (port 80, auto-opens browser)
npm run dev

# Build for production
npm run build:prod

# Build for staging
npm run build:stage

# Preview production build
npm run preview
```

## Project Structure Patterns

### Backend Package Structure
```
com.feian
├── common/           # Shared utilities and constants
├── framework/        # Security, config, aspects
├── system/           # System management modules
└── web.controller/   # REST API controllers
    ├── common/       # Common endpoints (captcha, etc.)
    ├── monitor/      # System monitoring
    ├── system/       # System management
    └── tool/         # Development tools
```

### Frontend Structure (Both Versions)
```
src/
├── api/              # HTTP API definitions
├── assets/           # Static assets (images, styles)
├── components/       # Reusable Vue components
├── layout/           # Layout components (sidebar, navbar)
├── router/           # Route definitions
├── store/            # State management (Vuex/Pinia)
├── utils/            # Utility functions
└── views/            # Page components
    ├── system/       # System management pages
    ├── monitor/      # Monitoring pages
    └── tool/         # Development tools
```

## Key Configuration Files

### Backend Configuration
- `application.yml`: Main application configuration
- `application-druid.yml`: Database connection settings
- `pom.xml`: Maven dependencies and build configuration
- `feian-admin/src/main/resources/logback.xml`: Logging configuration

### Frontend Configuration (Vue 2)
- `feian-ui/vue.config.js`: Vue CLI configuration
- `feian-ui/package.json`: Dependencies and scripts

### Frontend Configuration (Vue 3)
- `vite.config.js`: Vite build configuration with proxy settings
- `package.json`: Dependencies and scripts
- `.env.development`: Development environment variables
- `.env.production`: Production environment variables  
- `.env.staging`: Staging environment variables

## Development Workflow

### Code Generation
The system includes a powerful code generator accessible at `/tool/gen` that can:
- Generate CRUD operations for database tables
- Create frontend pages, API calls, and backend services
- Support both Vue 2 and Vue 3 templates

### Security & Authentication
- JWT-based authentication with configurable expiration (30 minutes default)
- Role-based access control with data scope permissions
- XSS protection and input validation
- Rate limiting on sensitive endpoints

### API Development
- RESTful API design with consistent response format (AjaxResult)
- Springdoc integration for API documentation at `/swagger-ui.html`
- Standardized error handling and validation

## Testing and Build

### Backend Testing
```bash
# Run tests
mvn test

# Package without tests
mvn clean package -DskipTests
```

### Frontend Testing and Linting
The Vue 3 frontend uses Vite for building and development. No specific testing framework is configured in the current setup - tests would need to be added as needed.

## Database Management

### Connection Configuration
- Default: MySQL 8.2.0 on localhost:3306
- Druid connection pool with monitoring at `/druid`
- Redis cache on localhost:6379

### Schema Management
- Database schema: `sql/ry_20250522.sql`
- Quartz scheduler tables: `sql/quartz.sql`

## Deployment Notes

### Backend Deployment
- Built JAR: `feian-admin/target/feian-admin.jar`
- Default port: 8080
- Use `ry.sh` script for service management in production

### Frontend Deployment
- Build output: `dist/` directory
- Development server proxies `/dev-api` requests to `http://localhost:8080`
- Production builds include gzip compression and optimized assets
- Static assets organized in `static/js/`, `static/css/`, etc.

## System Features

The system provides a comprehensive enterprise management platform including:
- User, role, and department management
- Menu and permission configuration
- System monitoring and logging
- Scheduled task management
- Dictionary and configuration management
- Code generation tools
- API documentation and testing

## Vue 3 Frontend Architecture Details

### Key Technologies & Dependencies
- **Vue 3.5.16**: Composition API with `<script setup>` syntax
- **Element Plus 2.9.9**: UI component library with Vue 3 support
- **Vite 6.3.5**: Modern build tool with hot module replacement
- **Pinia 3.0.2**: Vue 3 state management (replaces Vuex)
- **Vue Router 4.5.1**: Client-side routing
- **@vueuse/core**: Vue composition utilities
- **Axios 1.9.0**: HTTP client with request/response interceptors

### Build & Development Features
- **Hot Module Replacement (HMR)**: Instant updates during development
- **Auto-import**: Unplugin auto-import for Vue APIs and components
- **SVG Icons**: Vite plugin for SVG icon management
- **SCSS Support**: Sass preprocessing with embedded runtime
- **Gzip Compression**: Build-time compression for production
- **Code Splitting**: Automatic chunk splitting for optimal loading

### Frontend Architecture Patterns
- **Composition API**: Leverages Vue 3's modern reactivity system
- **Pinia Stores**: Modular state management with TypeScript support
- **Plugin System**: Centralized plugin registration and configuration
- **Component Auto-registration**: Automatic global component registration
- **Request Interceptors**: Centralized API error handling and authentication

### Environment Configuration
- **Development**: `/dev-api` proxy to `localhost:8080`, port 80
- **Production**: Static file serving with optimized assets
- **Staging**: Separate build configuration for staging deployments
- **Base URL**: Configurable deployment paths for different environments

## Claude Memory Updates
- Enhanced documentation with Vue 3 frontend architectural details (July 23, 2025)
- Added comprehensive build tool information and modern development practices
- Updated command references to reflect actual package.json scripts



## Business Modules (feian-tms)

### 1. 课件管理模块 (Courseware Management)
- **功能**: 管理理论和实践培训课件，包括教材、教案、PPT、CBT、工卡等
- **主要实体**: `Courseware`, `CoursewareFile`
- **特点**: 支持多种文件格式，按机型和专业分类管理

### 2. 培训计划模块 (Training Plan Management)
- **功能**: 制定和管理培训计划，包括课程安排和学时分配
- **主要实体**: `TrainingPlan`, `TrainingPlanDetail`
- **特点**: 灵活的计划制定，支持理论和实践培训时长规划

### 3. 培训大纲模块 (Training Outline Management)
- **功能**: 管理培训大纲和课程体系
- **主要实体**: `TrainingClass`, `ClassStudent`
- **特点**: 按机型、专业、培训能力组织班次

### 4. 学习模块 (Learning Management)
- **功能**: 管理学员学习过程和培训记录
- **主要实体**: `TrainingRecord`, `Student`
- **特点**: 详细记录学习进度和成绩评价

### 5. 证书模块 (Certificate Management)
- **功能**: 管理培训证书的颁发和管理
- **主要实体**: `Certificate`
- **特点**: 支持多种证书类型，包含有效期管理

### 6. 人员管理模块 (Personnel Management)
- **功能**: 管理学员和教员信息
- **主要实体**: `Student`, `Instructor`, `InstructorAbility`
- **特点**: 支持教员授课资质管理和学员培训档案

## 核心实体 (Core Entities)

### 基础配置实体
- **MachineType**: 机型管理（直升机型号、图片展示）
- **Major**: 专业管理（空勤/地勤）
- **TrainingType**: 培训类型（理论/实践）
- **TrainingAbility**: 培训能力（培训/复训/恢复考试）

### 课件相关实体
- **Courseware**: 课件主表（ATA章节、课程编码、培训时长）
- **CoursewareFile**: 课件文件（教材、教案、PPT、CBT等）

### 培训计划实体
- **TrainingPlan**: 培训计划主表
- **TrainingPlanDetail**: 培训计划明细（课程安排）

### 班次管理实体
- **TrainingClass**: 培训班次（班次编号、计划/实际日期）
- **ClassStudent**: 班次学员关联表

### 人员管理实体
- **Student**: 学员信息（学员档案、所属机型专业）
- **Instructor**: 教员信息（教员档案、授课资质）
- **StudentAbility**: 学员学习能力（学习进度、资质状态、培训成绩）

### 培训记录实体
- **TrainingRecord**: 培训记录（学习过程、成绩评价）
- **Certificate**: 证书管理（证书颁发、有效期管理）



### 系统运维管理
- **SystemLog**: 系统日志（操作日志、登录日志、异常日志）
- **Dictionary**: 数据字典（系统配置、下拉选项、参数管理）

### 文档管理
- **Manual**: 手册管理（培训手册、操作指南、规章制度）
- **ManualFile**: 手册文件（文档附件、版本管理、下载统计）

## 数据权限设计
- **机型维度**: 用户只能访问授权机型的相关数据
- **专业维度**: 区分空勤和地勤专业的数据访问权限
- **角色权限**: 管理员、教员、学员等不同角色的功能权限
- **多级权限**: 支持菜单权限、功能权限、数据权限的细粒度控制
- **审计日志**: 全面记录用户操作轨迹，支持安全审计

# 开发规范 (Development Standards)

## API设计规范

### 统一响应格式
- **严格使用** `R<T>` 作为统一响应格式，不要使用 `ResponseEntity.ok()`
- **避免多层包装**：直接返回 `R<T>` 而不是 `ResponseEntity<R<T>>`

```java
// ✅ 正确示例
@PostMapping("/login")
public R<LoginResponse> login(@RequestBody LoginRequest request) {
    return R.success(response);
}

// ❌ 错误示例
@PostMapping("/login")
public ResponseEntity<R<LoginResponse>> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(R.success(response));
}
```

### HTTP方法规范
- **统一使用 POST 方法**：所有API接口都使用 `@PostMapping`
- **请求参数规范**：
    - 所有查询和操作都使用 `@RequestBody` 接收参数
    - 混合提交（文件上传等）使用 `@ModelAttribute`
    - 避免使用 `@RequestParam` 和 `@PathVariable`

```java
// ✅ 正确示例
@PostMapping("/departments/list")
public R<Page<DepartmentResponse>> getDepartments(@RequestBody DepartmentQuery query) {
    return R.success(result);
}

@PostMapping("/departments/create")
public R<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest request) {
    return R.success(result);
}

@PostMapping("/files/upload")  
public R<FileResponse> uploadFile(@ModelAttribute FileUploadRequest request) {
    return R.success(result);
}

// ❌ 错误示例
@GetMapping("/departments")
public R<Page<DepartmentResponse>> getDepartments(@RequestParam String name) {
    return R.success(result);
}
```

### 通用工具规范
- **严格使用 com.feian.tms.common 包**：所有通用功能必须使用 com.feian.tms.common` 包中的工具类
- **禁止重复造轮子**：优先使用已有的 `BaseEntity`, `R<T>`, `HttpStatusEnum`, `PageRequest` 等
- **统一异常处理**：使用统一的异常处理机制

### Controller层规范
```java
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "部门管理", description = "部门管理相关接口")
public class DepartmentController {
    
    // 查询列表 - 使用PageHelper分页
    @PostMapping("/list")
    public R<PageInfo<DepartmentResponse>> list(@RequestBody PageRequest<DepartmentQuery> pageRequest) {
        // 启动分页
        PageUtils.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        
        // 查询数据
        List<Department> list = service.list(pageRequest.getQuery());
        
        // 转换为响应对象
        List<DepartmentResponse> responseList = list.stream()
            .map(this::convertToResponse)
            .toList();
        
        // 返回分页信息
        return R.success(new PageInfo<>(responseList));
    }
    
    // 获取详情
    @PostMapping("/detail")
    public R<DepartmentResponse> detail(@RequestBody IdRequest request) {
        return R.success(service.getById(request.getId()));
    }
    
    // 创建
    @PostMapping("/create")  
    public R<DepartmentResponse> create(@RequestBody DepartmentRequest request) {
        return R.success(service.create(request));
    }
    
    // 更新
    @PostMapping("/update")
    public R<DepartmentResponse> update(@RequestBody DepartmentUpdateRequest request) {
        return R.success(service.update(request));
    }
    
    // 删除
    @PostMapping("/delete")
    public R<Void> delete(@RequestBody IdRequest request) {
        service.delete(request.getId());
        return R.success();
    }
}
```

### 服务层规范
- **Service接口**：定义业务逻辑方法
- **Service实现类**：实现具体业务逻辑
- **Mapper接口**：数据访问层，使用 MyBatis-Plus-join
- **Mapper XML**：SQL语句定义，使用 MyBatis-Plus 的注解方式

```java

public interface DepartmentService extends MPJBaseService<Department> {
 
}

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends MPJBaseServiceImpl <DepartmentMapper, Department> implements DepartmentService {
    
   
}


@Mapper
public interface DepartmentMapper extends MPJBaseMapper<Department> {
}
```


### 请求/响应对象规范
- **Request对象**：用于接收前端请求参数
- **Response对象**：用于返回数据给前端
- **Query对象**：专门用于查询条件封装
- **通用对象**：如 `IdRequest` 用于只需要ID的场景

### 前端API调用规范
- 前端统一使用 POST 方法调用后端API
- 所有参数通过请求体传递
- 严格按照后端定义的Request对象格式传递数据
```

## Claude Memory
- Added comprehensive architecture and development guidelines documentation on May 23, 2024
- Documented key business modules, system features, and coding standards for the helicopter training management system
- Provided detailed explanations of backend and frontend architectures, project structure, and API design principles
- Added to-memorize content on May 25, 2024
- Memorized: to memorize