# TMS培训管理系统 - 机型权限实现总结

## 📋 实现概述

本次实现了基于机型的数据权限控制系统，确保学员用户只能访问其授权机型的相关数据。所有TMS相关代码都严格遵循模块化原则，放置在 `feian-tms` 包内。

## 🏗️ 架构设计

### 1. 核心实体关系
```
SysUser (系统用户)
    ├── studentId (关联学员ID)
    ├── currentMachineTypeId (当前选择的机型ID)
    └── currentMachineTypeName (当前选择的机型名称)

Student (学员)
    ├── userId (关联用户ID)
    └── StudentMachineType (多对多关联)
        └── MachineType (机型)
```

### 2. 包结构组织
```
com.feian.tms/
├── controller/
│   └── MachineTypeSelectionController.java    # 机型选择控制器
├── domain/
│   ├── Student.java                           # 学员实体（已扩展）
│   └── StudentMachineType.java                # 学员机型关联实体
├── mapper/
│   └── StudentMachineTypeMapper.java          # 学员机型关联Mapper
├── service/
│   ├── IStudentMachineTypeService.java        # 学员机型关联服务接口
│   └── impl/
│       └── StudentMachineTypeServiceImpl.java # 学员机型关联服务实现
```

## 🔧 核心功能实现

### 1. 学员与用户关联关系 ✅

**后端实现：**
- 扩展 `SysUser` 实体：添加 `studentId`, `currentMachineTypeId`, `currentMachineTypeName` 字段
- 扩展 `Student` 实体：添加 `userId` 字段建立双向关联
- 数据库表结构：`student_user_association.sql`

**关键代码位置：**
- `/feian-common/src/main/java/com/feian/common/core/domain/entity/SysUser.java`
- `/feian-tms/src/main/java/com/feian/tms/domain/Student.java`

### 2. 学员与机型多对多关联 ✅

**后端实现：**
- 创建 `StudentMachineType` 关联实体支持一个学员关联多个机型
- 实现完整的CRUD操作和业务逻辑
- 支持主要机型设置和权限验证

**关键代码位置：**
- `/feian-tms/src/main/java/com/feian/tms/domain/StudentMachineType.java`
- `/feian-tms/src/main/java/com/feian/tms/service/IStudentMachineTypeService.java`
- `/feian-tms/src/main/java/com/feian/tms/service/impl/StudentMachineTypeServiceImpl.java`

### 3. 登录时机型选择功能 ✅

**后端实现：**
- 机型选择控制器提供完整的API接口
- 支持获取可选机型列表、设置选择、获取当前选择、清除选择
- 集成安全验证确保用户只能选择授权的机型

**前端实现：**
- 机型选择组件提供可视化界面
- 卡片式布局展示机型信息
- 支持机型图片、名称、代码、描述显示

**关键代码位置：**
- `/feian-tms/src/main/java/com/feian/tms/controller/MachineTypeSelectionController.java`
- `/src/api/tms/machineTypeSelection.js`
- `/src/components/MachineTypeSelector/index.vue`

### 4. 数据权限过滤系统 ✅

**核心特性：**
- 扩展 `@DataScope` 注解支持机型数据过滤
- 增强切面处理机型权限逻辑
- 支持多种过滤模式：机型表直接过滤、学员关联表过滤、默认字段过滤

**关键代码位置：**
- `/feian-common/src/main/java/com/feian/common/annotation/DataScope.java`
- `/feian-framework/src/main/java/com/feian/framework/aspectj/DataScopeAspect.java`

## 📊 数据库设计

### 新增表结构
```sql
-- 学员机型关联表
CREATE TABLE tms_student_machine_type (
  relation_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  student_id BIGINT NOT NULL COMMENT '学员ID',
  machine_type_id BIGINT NOT NULL COMMENT '机型ID',
  is_primary CHAR(1) DEFAULT '0' COMMENT '是否为主要机型（0否 1是）',
  status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  -- 标准字段...
  PRIMARY KEY (relation_id),
  UNIQUE KEY uk_student_machine (student_id, machine_type_id)
);
```

### 扩展字段
```sql
-- sys_user表扩展
ALTER TABLE sys_user ADD COLUMN student_id BIGINT COMMENT '学员ID';
ALTER TABLE sys_user ADD COLUMN current_machine_type_id BIGINT COMMENT '当前选择的机型ID';
ALTER TABLE sys_user ADD COLUMN current_machine_type_name VARCHAR(100) COMMENT '当前选择的机型名称';

-- tms_student表扩展
ALTER TABLE tms_student ADD COLUMN user_id BIGINT COMMENT '关联的用户ID';
```

## 🔐 权限控制流程

### 用户登录流程
1. **用户认证** → 系统验证用户身份
2. **学员检测** → 检查用户是否关联学员信息
3. **机型权限检查** → 获取学员可访问的机型列表
4. **机型选择** → 用户选择要访问的机型
5. **权限激活** → 系统激活对应机型的数据访问权限

### 数据访问控制
1. **请求拦截** → `@DataScope` 注解标记需要过滤的方法
2. **用户信息获取** → 从Security上下文获取当前用户和选择的机型
3. **SQL条件注入** → 根据机型ID动态生成过滤条件
4. **数据返回** → 只返回用户有权限访问的数据

## 🎯 使用示例

### 1. 控制器中启用机型过滤
```java
@PostMapping("/list")
@DataScope(enableMachineTypeFilter = true, studentAlias = "s")
public R<Page<StudentResponse>> list(@RequestBody StudentQuery query) {
    // 方法实现...
}
```

### 2. 前端机型选择
```javascript
// 获取可选机型
const machineTypes = await getAvailableMachineTypes();

// 选择机型
await selectMachineType({
  machineTypeId: 1,
  machineTypeName: 'EC135'
});
```

## 🚀 部署说明

### 1. 数据库初始化
```bash
# 执行学员用户关联SQL
mysql -u root -p < student_user_association.sql

# 执行菜单初始化SQL  
mysql -u root -p < tms_menu.sql
```

### 2. 前端组件注册
```javascript
// 在需要的页面中使用机型选择器
import MachineTypeSelector from '@/components/MachineTypeSelector'
```

## ✅ 功能验证清单

- [x] 学员与用户双向关联
- [x] 学员与机型多对多关联
- [x] 登录时机型选择界面
- [x] 机型权限验证
- [x] 数据权限过滤
- [x] 退出时清理机型选择
- [x] 完整的API接口
- [x] 前端可视化组件
- [x] 数据库表结构
- [x] 权限控制注解

## 🔧 技术特点

### 安全性
- 严格的权限验证，用户只能选择授权的机型
- SQL注入防护，使用参数化查询
- 管理员用户不受机型权限限制

### 可扩展性
- 模块化设计，易于扩展新的权限维度
- 插件式的权限过滤机制
- 灵活的注解配置

### 用户体验
- 可视化的机型选择界面
- 卡片式布局展示机型信息
- 响应式设计适配不同屏幕

## 📝 注意事项

1. **包结构规范**：所有TMS相关代码必须放在 `feian-tms` 包内
2. **数据一致性**：确保学员机型关联数据的完整性
3. **性能优化**：大量数据时考虑添加索引和缓存
4. **权限边界**：管理员用户不受机型权限限制
5. **异常处理**：完善的错误处理和用户提示

---

**实现完成时间**：2025年1月23日  
**技术栈**：Spring Boot 3.3.5 + Vue 3.5.16 + MyBatis-Plus + Element Plus  
**作者**：Claude Code Assistant