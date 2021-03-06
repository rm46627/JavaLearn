import { Role } from "src/app/models/role.enum"

export interface User {
    id: bigint
    username: string
    password: string
    email: string
    enabled: boolean
    accessToken: string
    refreshToken: string
    role: Role
}